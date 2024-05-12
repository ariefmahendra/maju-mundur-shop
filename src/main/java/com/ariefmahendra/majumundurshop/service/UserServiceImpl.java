package com.ariefmahendra.majumundurshop.service;

import com.ariefmahendra.majumundurshop.security.SecurityConfig;
import com.ariefmahendra.majumundurshop.dto.UserRequestDto;
import com.ariefmahendra.majumundurshop.dto.UserResponseDto;
import com.ariefmahendra.majumundurshop.entity.UserEntity;
import com.ariefmahendra.majumundurshop.exception.DuplicateException;
import com.ariefmahendra.majumundurshop.exception.NotFoundException;
import com.ariefmahendra.majumundurshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;

    public UserServiceImpl(UserRepository userRepository, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto payload) throws DuplicateException {
        UserEntity user = Stream.of(new UserEntity()).peek(
                u -> {
                    u.setName(payload.getName());
                    u.setEmail(payload.getEmail());
                    u.setPassword(securityConfig.passwordEncoder().encode(payload.getPassword()));
                    u.setPhoneNumber(payload.getPhoneNumber());
                }
        ).findFirst().orElse(null);

        if (this.userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new DuplicateException("user already exist");
        }

        UserEntity userEntity = this.userRepository.save(user);

        return Stream.of(new UserResponseDto()).peek(
                u -> {
                    u.setId(userEntity.getId());
                    u.setName(userEntity.getName());
                    u.setEmail(userEntity.getEmail());
                    u.setPhoneNumber(userEntity.getPhoneNumber());
                    u.setRewards(userEntity.getRewards());
                }
        ).findFirst().orElse(null);
    }

    @Override
    public UserResponseDto updateUser(String id, UserRequestDto payload) throws NotFoundException {
        UserEntity userById = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        UserEntity userEntity = Stream.of(new UserEntity()).peek(
                u -> {
                    u.setId(id);
                    u.setName(payload.getName());
                    u.setEmail(payload.getEmail());
                    u.setPassword(securityConfig.passwordEncoder().encode(payload.getPassword()));
                    u.setPhoneNumber(payload.getPhoneNumber());
                    u.setRewards(userById.getRewards());
                }
        ).findFirst().orElse(null);

        UserEntity user = this.userRepository.save(userEntity);

        return Stream.of(new UserResponseDto()).peek(
                u -> {
                    u.setId(user.getId());
                    u.setName(user.getName());
                    u.setEmail(user.getEmail());
                    u.setPhoneNumber(user.getPhoneNumber());
                    u.setRewards(user.getRewards());
                }
        ).findFirst().orElse(null);
    }

    @Override
    public UserResponseDto getUserById(String id) throws NotFoundException {
        UserEntity user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        return Stream.of(new UserResponseDto()).peek(
                u -> {
                    u.setId(user.getId());
                    u.setName(user.getName());
                    u.setEmail(user.getEmail());
                    u.setPhoneNumber(user.getPhoneNumber());
                    u.setRewards(user.getRewards());
                }
        ).findFirst().orElse(null);
    }

    @Override
    public void deleteUser(String id) throws NotFoundException {
        this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        this.userRepository.deleteById(id);
    }

    @Override
    public List<UserResponseDto> getListUserBoughtProduct() {
        List<UserEntity> userBoughtProducts = this.userRepository.findUserBoughtProducts();

        return userBoughtProducts.stream().map(userEntity -> {
            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setId(userEntity.getId());
            userResponseDto.setName(userEntity.getName());
            userResponseDto.setEmail(userEntity.getEmail());
            userResponseDto.setPhoneNumber(userEntity.getPhoneNumber());
            userResponseDto.setRewards(userEntity.getRewards());

            return userResponseDto;
        }).toList();
    }
}
