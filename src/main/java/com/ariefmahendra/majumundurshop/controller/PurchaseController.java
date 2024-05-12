package com.ariefmahendra.majumundurshop.controller;


import com.ariefmahendra.majumundurshop.dto.PurchaseRequestDto;
import com.ariefmahendra.majumundurshop.dto.PurchaseResponseDto;
import com.ariefmahendra.majumundurshop.dto.WebResponse;
import com.ariefmahendra.majumundurshop.service.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/api/v1/purchases")
    public WebResponse<PurchaseResponseDto> create(@RequestBody PurchaseRequestDto payload) throws Exception {
        PurchaseResponseDto purchaseResponseDto = this.purchaseService.create(payload);

        return WebResponse.<PurchaseResponseDto>builder()
                .code(HttpStatus.CREATED)
                .message("success create purchase")
                .data(purchaseResponseDto)
                .build();
    }
}
