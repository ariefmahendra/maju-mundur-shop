package com.ariefmahendra.majumundurshop.repository;

import com.ariefmahendra.majumundurshop.entity.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<MerchantEntity, String> {

    Optional<MerchantEntity> findByEmail(String email);
}
