package com.ariefmahendra.majumundurshop.repository;

import com.ariefmahendra.majumundurshop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
}
