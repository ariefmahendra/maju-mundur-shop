package com.ariefmahendra.majumundurshop.service;

import com.ariefmahendra.majumundurshop.dto.PurchaseRequestDto;
import com.ariefmahendra.majumundurshop.dto.PurchaseResponseDto;

public interface PurchaseService {

    PurchaseResponseDto create(PurchaseRequestDto payload) throws Exception;

}
