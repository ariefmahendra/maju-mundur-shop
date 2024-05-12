package com.ariefmahendra.majumundurshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDetailRequestDto {
    private String productId;
    private Integer quantity;

    @Override
    public String toString() {
        return "PurchaseDetailRequestDto{" +
                "productId='" + productId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
