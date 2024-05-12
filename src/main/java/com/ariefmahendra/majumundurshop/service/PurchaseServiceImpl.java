package com.ariefmahendra.majumundurshop.service;

import com.ariefmahendra.majumundurshop.dto.PurchaseDetailRequestDto;
import com.ariefmahendra.majumundurshop.dto.PurchaseDetailResponseDto;
import com.ariefmahendra.majumundurshop.dto.PurchaseRequestDto;
import com.ariefmahendra.majumundurshop.dto.PurchaseResponseDto;
import com.ariefmahendra.majumundurshop.entity.ProductEntity;
import com.ariefmahendra.majumundurshop.entity.PurchaseDetailEntity;
import com.ariefmahendra.majumundurshop.entity.PurchaseEntity;
import com.ariefmahendra.majumundurshop.entity.UserEntity;
import com.ariefmahendra.majumundurshop.exception.NotFoundException;
import com.ariefmahendra.majumundurshop.exception.UnauthorizedException;
import com.ariefmahendra.majumundurshop.repository.ProductRepository;
import com.ariefmahendra.majumundurshop.repository.PurchaseDetailRepository;
import com.ariefmahendra.majumundurshop.repository.PurchaseRepository;
import com.ariefmahendra.majumundurshop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class PurchaseServiceImpl implements PurchaseService{
    private final PurchaseRepository purchaseRepository;
    private final PurchaseDetailRepository purchaseDetailRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, PurchaseDetailRepository purchaseDetailRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.purchaseRepository = purchaseRepository;
        this.purchaseDetailRepository = purchaseDetailRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public PurchaseResponseDto create(PurchaseRequestDto payload) throws Exception {
        // find user by id from payload
        UserEntity user = this.userRepository.findById(payload.getUserId()).orElseThrow(() -> new NotFoundException("User not found"));

        // set purchase
        PurchaseEntity purchase = Stream.of(new PurchaseEntity()).peek(
                p -> {
                    p.setPurchaseDate(payload.getTransactionDate());
                    p.setUser(user);
                }
        ).findFirst().orElse(null);

        // save purchase
        PurchaseEntity purchaseSaved = this.purchaseRepository.save(purchase);

        // variable to store total transaction
        Long total = 0L;

        // find product by id from payload
        List<PurchaseDetailRequestDto> purchaseDetails = payload.getPurchaseDetails();
        List<PurchaseDetailResponseDto> purchaseDetailResponses = new ArrayList<>();
        for (PurchaseDetailRequestDto purchaseDetail : purchaseDetails) {
            String productId = purchaseDetail.getProductId();
            ProductEntity product = this.productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));

            // set stock product
            int newStock = product.getStock() - purchaseDetail.getQuantity();
            if (newStock < 0) {
                throw new Exception("Stock not enough");
            }
            product.setStock(newStock);

            // update stock of product
            ProductEntity productSaved = this.productRepository.save(product);

            // set total transaction
            total += productSaved.getProductPrice() * purchaseDetail.getQuantity();

            // save transaction detail
            PurchaseDetailEntity purchaseDetailEntity = Stream.of(new PurchaseDetailEntity()).peek(
                    pd -> {
                        pd.setProduct(productSaved);
                        pd.setPurchase(purchaseSaved);
                        pd.setQty(purchaseDetail.getQuantity());
                    }
            ).findFirst().orElse(null);
            PurchaseDetailEntity purchaseDetailSaved = this.purchaseDetailRepository.save(purchaseDetailEntity);

            // store to response dto of purchase detail to list
            PurchaseDetailResponseDto purchaseDetailResponseDto = Stream.of(new PurchaseDetailResponseDto()).peek(
                    pd -> {
                        pd.setId(purchaseDetailSaved.getId());
                        pd.setProduct(productSaved);
                        pd.setQty(purchaseDetail.getQuantity());
                    }
            ).findFirst().orElse(null);
            purchaseDetailResponses.add(purchaseDetailResponseDto);
        }

        // update total purchase
        purchaseSaved.setTotal(total);
        PurchaseEntity purchaseTotalUpdated = this.purchaseRepository.save(purchaseSaved);

        // update rewards user
        Random random = new Random();
        boolean randomBoolean = random.nextBoolean();
        if (user.getRewards() == null) {
            user.setRewards(0L);
        }
        user.setRewards(randomBoolean ? user.getRewards() + 40 : user.getRewards() + 20);
        UserEntity userUpdated = this.userRepository.save(user);

        return Stream.of(new PurchaseResponseDto()).peek(
                p -> {
                    p.setId(purchaseTotalUpdated.getId());
                    p.setTransactionDate(purchaseTotalUpdated.getPurchaseDate());
                    p.setUserId(userUpdated.getId());
                    p.setPurchaseDetails(purchaseDetailResponses);
                    p.setTotal(purchaseTotalUpdated.getTotal());
                }
        ).findFirst().orElse(null);
    }
}
