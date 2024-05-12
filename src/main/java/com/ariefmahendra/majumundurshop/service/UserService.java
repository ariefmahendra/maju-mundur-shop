package com.ariefmahendra.majumundurshop.service;

import com.ariefmahendra.majumundurshop.dto.UserRequestDto;
import com.ariefmahendra.majumundurshop.dto.UserResponseDto;
import com.ariefmahendra.majumundurshop.exception.DuplicateException;
import com.ariefmahendra.majumundurshop.exception.NotFoundException;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto payload) throws DuplicateException;

    UserResponseDto updateUser(String id, UserRequestDto payload) throws NotFoundException;

    UserResponseDto getUserById(String id) throws NotFoundException;

    void deleteUser(String id) throws NotFoundException;

    List<UserResponseDto> getListUserBoughtProduct();

}
