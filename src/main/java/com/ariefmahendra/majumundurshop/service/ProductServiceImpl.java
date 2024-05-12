package com.ariefmahendra.majumundurshop.service;

import com.ariefmahendra.majumundurshop.dto.ProductRequestDto;
import com.ariefmahendra.majumundurshop.entity.ProductEntity;
import com.ariefmahendra.majumundurshop.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductEntity createProduct(ProductRequestDto payload) {
        ProductEntity product = new ProductEntity();
        product.setProductName(payload.getProductName());
        product.setProductCode(payload.getProductCode());
        product.setProductPrice(payload.getProductPrice());
        product.setStock(payload.getStock());

        return this.productRepository.save(product);
    }

    @Override
    public ProductEntity updateProduct(String id, ProductRequestDto payload) {
        ProductEntity product = new ProductEntity();
        product.setId(id);
        product.setProductName(payload.getProductName());
        product.setProductCode(payload.getProductCode());
        product.setProductPrice(payload.getProductPrice());
        product.setStock(payload.getStock());

        return this.productRepository.save(product);
    }

    @Override
    public void deleteProduct(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public List<ProductEntity> getListProducts() {
        return this.productRepository.findAll();
    }
}
