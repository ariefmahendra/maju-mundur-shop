package com.ariefmahendra.majumundurshop.controller;

import com.ariefmahendra.majumundurshop.dto.UserRequestDto;
import com.ariefmahendra.majumundurshop.dto.UserResponseDto;
import com.ariefmahendra.majumundurshop.dto.WebResponse;
import com.ariefmahendra.majumundurshop.exception.DuplicateException;
import com.ariefmahendra.majumundurshop.exception.NotFoundException;
import com.ariefmahendra.majumundurshop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/users")
    public WebResponse<UserResponseDto> createUser(@RequestBody UserRequestDto payload) throws DuplicateException {
        UserResponseDto user = this.userService.createUser(payload);
        return WebResponse.<UserResponseDto>builder()
                .code(HttpStatus.CREATED)
                .message("User created successfully")
                .data(user).build();
    }

    @PutMapping("/api/v1/users/{id}")
    public WebResponse<UserResponseDto> updateUser(@PathVariable String id,@RequestBody UserRequestDto payload) throws NotFoundException {
        UserResponseDto response = this.userService.updateUser(id, payload);
        return WebResponse.<UserResponseDto>builder()
                .code(HttpStatus.OK)
                .message("User updated successfully")
                .data(response)
                .build();
    }

    @GetMapping("/api/v1/users/{id}")
    public WebResponse<UserResponseDto> getById(@PathVariable String id) throws NotFoundException {
        UserResponseDto user = this.userService.getUserById(id);
        return WebResponse.<UserResponseDto>builder()
                .code(HttpStatus.OK)
                .message("User retrieved successfully")
                .data(user)
                .build();
    }

    @DeleteMapping("/api/v1/users/{id}")
    public WebResponse<String> deleteUserById(@PathVariable String id) throws NotFoundException {
        this.userService.deleteUser(id);
        return WebResponse.<String>builder()
                .code(HttpStatus.OK)
                .message("User deleted successfully")
                .build();
    }

    @GetMapping("/api/v1/users/products")
    public WebResponse<List<UserResponseDto>> getListUserBoughtProduct() {
        List<UserResponseDto> listUserBoughtProduct = this.userService.getListUserBoughtProduct();

        return WebResponse.<List<UserResponseDto>>builder()
                .code(HttpStatus.OK)
                .message("User list retrieved successfully")
                .data(listUserBoughtProduct)
                .build();
    }

}
