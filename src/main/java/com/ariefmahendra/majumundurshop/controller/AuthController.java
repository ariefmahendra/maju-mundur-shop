package com.ariefmahendra.majumundurshop.controller;

import com.ariefmahendra.majumundurshop.dto.AuthRequestDto;
import com.ariefmahendra.majumundurshop.dto.LoginResponseDto;
import com.ariefmahendra.majumundurshop.dto.WebResponse;
import com.ariefmahendra.majumundurshop.exception.NotFoundException;
import com.ariefmahendra.majumundurshop.exception.UnauthorizedException;
import com.ariefmahendra.majumundurshop.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/api/v1/auth/login")
    public WebResponse<LoginResponseDto> login(@RequestBody AuthRequestDto loginRequestDto) throws UnauthorizedException, NotFoundException {
        LoginResponseDto loginRes = authService.login(loginRequestDto);

        return WebResponse.<LoginResponseDto>builder()
                .code(HttpStatus.CREATED)
                .message("Login Success")
                .data(loginRes)
                .build();
    }
}
