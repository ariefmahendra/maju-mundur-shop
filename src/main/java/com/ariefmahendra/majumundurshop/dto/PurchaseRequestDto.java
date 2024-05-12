package com.ariefmahendra.majumundurshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequestDto {

    private String transactionDate;
    private String userId;

    private List<PurchaseDetailRequestDto> purchaseDetails;

    @Override
    public String toString() {
        return "PurchaseRequestDto{" +
                "transactionDate='" + transactionDate + '\'' +
                ", userId='" + userId + '\'' +
                ", purchaseDetails=" + purchaseDetails +
                '}';
    }
}
