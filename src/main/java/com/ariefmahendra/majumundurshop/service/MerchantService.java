package com.ariefmahendra.majumundurshop.service;

import com.ariefmahendra.majumundurshop.dto.MerchantResponseDto;
import com.ariefmahendra.majumundurshop.entity.MerchantEntity;
import com.ariefmahendra.majumundurshop.exception.DuplicateException;
import com.ariefmahendra.majumundurshop.exception.NotFoundException;

public interface MerchantService {

    MerchantResponseDto save(MerchantEntity merchantEntity) throws DuplicateException;

    MerchantResponseDto update(MerchantEntity merchantEntity) throws NotFoundException, DuplicateException;

    MerchantResponseDto findById(String id) throws NotFoundException;

    void deleteById(String id) throws NotFoundException;

}
