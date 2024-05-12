package com.ariefmahendra.majumundurshop.service;

import com.ariefmahendra.majumundurshop.dto.MerchantResponseDto;
import com.ariefmahendra.majumundurshop.entity.MerchantEntity;
import com.ariefmahendra.majumundurshop.exception.DuplicateException;
import com.ariefmahendra.majumundurshop.exception.NotFoundException;
import com.ariefmahendra.majumundurshop.repository.MerchantRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;
    private final PasswordEncoder passwordEncoder;

    public MerchantServiceImpl(MerchantRepository merchantRepository, PasswordEncoder passwordEncoder) {
        this.merchantRepository = merchantRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MerchantResponseDto save(MerchantEntity merchantEntity) throws DuplicateException {
        Optional<MerchantEntity> byEmail = merchantRepository.findByEmail(merchantEntity.getEmail());
        if (byEmail.isPresent()) {
            throw new DuplicateException("Email already exist");
        }

        merchantEntity.setPassword(passwordEncoder.encode(merchantEntity.getPassword()));
        MerchantEntity save = merchantRepository.save(merchantEntity);

        return new MerchantResponseDto(save.getId(), save.getEmail());
    }

    @Override
    public MerchantResponseDto update(MerchantEntity merchantEntity) throws NotFoundException, DuplicateException {

        merchantRepository.findById(merchantEntity.getId()).orElseThrow(() -> new NotFoundException("Merchant not found"));


        Optional<MerchantEntity> byEmail = merchantRepository.findByEmail(merchantEntity.getEmail());
        if (byEmail.isPresent()) {
            throw new DuplicateException("Email already exist");
        }

        merchantEntity.setPassword(passwordEncoder.encode(merchantEntity.getPassword()));

        System.out.println(merchantEntity.getId());
        MerchantEntity save = merchantRepository.save(merchantEntity);

        return new MerchantResponseDto(save.getId(), save.getEmail());
    }

    @Override
    public MerchantResponseDto findById(String id) throws NotFoundException {
        MerchantEntity merchant = merchantRepository.findById(id).orElseThrow(() -> new NotFoundException("Merchant not found"));

        return new MerchantResponseDto(merchant.getId(), merchant.getEmail());
    }

    @Override
    public void deleteById(String id) throws NotFoundException {
        merchantRepository.findById(id).orElseThrow(() -> new NotFoundException("Merchant not found"));
        merchantRepository.deleteById(id);
    }
}
