package com.ariefmahendra.majumundurshop.service;

import com.ariefmahendra.majumundurshop.dto.AuthRequestDto;
import com.ariefmahendra.majumundurshop.dto.LoginResponseDto;
import com.ariefmahendra.majumundurshop.dto.RegisterResponseDto;
import com.ariefmahendra.majumundurshop.exception.NotFoundException;
import com.ariefmahendra.majumundurshop.exception.UnauthorizedException;

public interface AuthService {
    LoginResponseDto login(AuthRequestDto loginRequestDto) throws NotFoundException, UnauthorizedException;
    RegisterResponseDto register(AuthRequestDto registerRequestDto);
}
