package com.ariefmahendra.majumundurshop.controller;

import com.ariefmahendra.majumundurshop.dto.ProductRequestDto;
import com.ariefmahendra.majumundurshop.dto.WebResponse;
import com.ariefmahendra.majumundurshop.entity.ProductEntity;
import com.ariefmahendra.majumundurshop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/api/v1/products")
    public WebResponse<ProductEntity> createProduct(@RequestBody ProductRequestDto payload) {
        ProductEntity product = productService.createProduct(payload);
        return WebResponse.<ProductEntity>builder()
                .code(HttpStatus.CREATED)
                .message("Product created successfully")
                .data(product)
                .build();

    }

    @PutMapping("/api/v1/products/{id}")
    public WebResponse<ProductEntity> updateProduct(@PathVariable String id, @RequestBody ProductRequestDto payload) {
        ProductEntity product = productService.updateProduct(id, payload);
        return WebResponse.<ProductEntity>builder()
                .data(product)
                .code(HttpStatus.OK)
                .message("Product updated successfully")
                .build();
    }

    @DeleteMapping("/api/v1/products/{id}")
    public WebResponse<String> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return WebResponse.<String>builder()
                .code(HttpStatus.OK)
                .message("Product deleted successfully")
                .build();
    }

    @GetMapping("/api/v1/products")
    public WebResponse<List<ProductEntity>> getListProducts() {
        List<ProductEntity> products = productService.getListProducts();
        return WebResponse.<List<ProductEntity>>builder()
                .code(HttpStatus.OK)
                .message("Products retrieved successfully")
                .data(products)
                .build();
    }
}
