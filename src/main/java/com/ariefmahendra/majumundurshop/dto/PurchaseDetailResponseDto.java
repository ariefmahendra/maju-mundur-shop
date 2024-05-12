package com.ariefmahendra.majumundurshop.dto;

import com.ariefmahendra.majumundurshop.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetailResponseDto {
    private String id;
    private ProductEntity product;
    private Integer qty;
}
