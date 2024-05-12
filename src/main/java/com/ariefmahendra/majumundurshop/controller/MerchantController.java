package com.ariefmahendra.majumundurshop.controller;

import com.ariefmahendra.majumundurshop.dto.MerchantResponseDto;
import com.ariefmahendra.majumundurshop.dto.WebResponse;
import com.ariefmahendra.majumundurshop.entity.MerchantEntity;
import com.ariefmahendra.majumundurshop.exception.DuplicateException;
import com.ariefmahendra.majumundurshop.exception.NotFoundException;
import com.ariefmahendra.majumundurshop.service.MerchantService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping("/api/v1/merchant")
    public WebResponse<MerchantResponseDto> create(@RequestBody MerchantEntity merchantEntity) throws DuplicateException {
        MerchantResponseDto merchantRes = merchantService.save(merchantEntity);
        return WebResponse.<MerchantResponseDto>builder()
                .code(HttpStatus.CREATED)
                .message("Merchant created")
                .data(merchantRes)
                .build();
    }

    @PutMapping("/api/v1/merchant/{id}")
    public WebResponse<MerchantResponseDto> update(@PathVariable String id, @RequestBody MerchantEntity payload) throws NotFoundException, DuplicateException {
        payload.setId(id);
        MerchantResponseDto merchant = merchantService.update(payload);
        return WebResponse.<MerchantResponseDto>builder()
                .code(HttpStatus.OK)
                .message("Merchant updated")
                .data(merchant)
                .build();
    }

    @GetMapping("/api/v1/merchant/{id}")
    public WebResponse<MerchantResponseDto> findById(@PathVariable String id) throws NotFoundException {
        MerchantResponseDto merchant = merchantService.findById(id);
        return WebResponse.<MerchantResponseDto>builder()
                .code(HttpStatus.OK)
                .message("Merchant found")
                .data(merchant)
                .build();
    }

    @DeleteMapping("/api/v1/merchant/{id}")
    public WebResponse<MerchantEntity> deleteById(@PathVariable String id) throws NotFoundException {
        merchantService.deleteById(id);
        return WebResponse.<MerchantEntity>builder()
                .code(HttpStatus.OK)
                .message("Merchant deleted")
                .build();
    }
}
