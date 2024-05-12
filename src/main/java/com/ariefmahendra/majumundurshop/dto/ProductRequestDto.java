package com.ariefmahendra.majumundurshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    private String productName;

    private String productCode;

    private Long productPrice;

    private Integer stock;
}
