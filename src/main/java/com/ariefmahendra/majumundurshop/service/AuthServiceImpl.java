package com.ariefmahendra.majumundurshop.service;

import com.ariefmahendra.majumundurshop.security.SecurityConfig;
import com.ariefmahendra.majumundurshop.dto.AuthRequestDto;
import com.ariefmahendra.majumundurshop.dto.LoginResponseDto;
import com.ariefmahendra.majumundurshop.dto.RegisterResponseDto;
import com.ariefmahendra.majumundurshop.entity.MerchantEntity;
import com.ariefmahendra.majumundurshop.entity.UserEntity;
import com.ariefmahendra.majumundurshop.exception.NotFoundException;
import com.ariefmahendra.majumundurshop.exception.UnauthorizedException;
import com.ariefmahendra.majumundurshop.repository.MerchantRepository;
import com.ariefmahendra.majumundurshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final MerchantRepository merchantRepository;
    private final SecurityConfig securityConfig;

    public AuthServiceImpl(UserRepository userRepository, MerchantRepository merchantRepository, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.merchantRepository = merchantRepository;
        this.securityConfig = securityConfig;
    }
    @Override
    public LoginResponseDto login(AuthRequestDto loginRequestDto) throws NotFoundException, UnauthorizedException {
        Optional<UserEntity> user = this.userRepository.findByEmail(loginRequestDto.getEmail());
        Optional<MerchantEntity> merchant = this.merchantRepository.findByEmail(loginRequestDto.getEmail());

        if (user.isPresent()){
            UserEntity userEntity = user.get();
            if (securityConfig.passwordEncoder().matches(loginRequestDto.getPassword(), userEntity.getPassword())) {
                return new LoginResponseDto(userEntity.getEmail());
            } else {
                throw new UnauthorizedException("Unauthorized");
            }
        } else if (merchant.isPresent()) {
            MerchantEntity merchantEntity = merchant.get();
            if (securityConfig.passwordEncoder().matches(loginRequestDto.getPassword(), merchantEntity.getPassword())) {
                return new LoginResponseDto(merchantEntity.getEmail());
            } else {
                throw new UnauthorizedException("Unauthorized");
            }
        } else {
            throw new NotFoundException("User not found");
        }
    }

    @Override
    public RegisterResponseDto register(AuthRequestDto registerRequestDto) {
        return null;
    }
}
