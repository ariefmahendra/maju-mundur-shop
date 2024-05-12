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
public class PurchaseResponseDto {
    private String id;
    private String transactionDate;
    private Long total;
    private String userId;

    private List<PurchaseDetailResponseDto> purchaseDetails;
}
