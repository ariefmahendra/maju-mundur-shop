package com.ariefmahendra.majumundurshop.service;

import com.ariefmahendra.majumundurshop.dto.ProductRequestDto;
import com.ariefmahendra.majumundurshop.entity.ProductEntity;

import java.util.List;

public interface ProductService {

    ProductEntity createProduct(ProductRequestDto payload);

    ProductEntity updateProduct(String id, ProductRequestDto payload);

    void deleteProduct(String id);

    List<ProductEntity> getListProducts();

}
