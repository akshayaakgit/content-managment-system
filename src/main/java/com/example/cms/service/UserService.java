package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponse;
import com.example.cms.entity.User;
import com.example.cms.utility.ResponseStructure;

import jakarta.validation.Valid;

public interface UserService {

	ResponseEntity<ResponseStructure<UserResponse>> registerUser(@Valid UserRequest user);

	ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId);

	ResponseEntity<ResponseStructure<UserResponse>> getUser(int userId);

}
