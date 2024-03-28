package com.example.cms.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponse;
import com.example.cms.entity.User;
import com.example.cms.exception.UserAlreadyExistExcepption;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.repository.UserRepository;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	private ResponseStructure<UserResponse> responseStructure;
	private PasswordEncoder passwordEncoder;

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest) {
		if (userRepository.existsByEmail(userRequest.getEmail()))
			throw new UserAlreadyExistExcepption("email already exists");
		User user = userRepository.save(mapToUserEntity(userRequest, new User()));
		return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
				.setMessage("User registerd successfully").setData(mapToUserResponse(user)));
	}

	private UserResponse mapToUserResponse(User user) {
		return UserResponse.builder().userId(user.getUserId()).email(user.getEmail()).username(user.getUsername())
				.createdAt(user.getCreatedAt()).lastModifiedAt(user.getLastModifiedAt()).deleted(user.getDeleted())
				.build();

	}

	private User mapToUserEntity(UserRequest userRequest, User user) {
		user.setEmail(userRequest.getEmail());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		user.setUsername(userRequest.getUsername());
		user.setDeleted(false);
		return user;

	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId) {
		return userRepository.findById(userId).map(user -> {
			user.setDeleted(true);
			user = userRepository.save(user);
			return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
					.setMessage("User have been founded Successfully!!").setData(mapToUserResponse(user)));
		}).orElseThrow(() -> new UserNotFoundByIdException("User not found by id"));

//			return ResponseEntity.status(HttpStatus.OK.value()).body(responseStructure.setData(mapToUserResponse(user))
//					.setMessage("aa").setStatus(HttpStatus.OK.value()));

	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> getUser(int userId) {
		return userRepository.findById(userId).map(user -> {
			return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
					.setMessage("User have been founded Successfully!!").setData(mapToUserResponse(user)));
		}).orElseThrow(() -> new UserNotFoundByIdException("User not found by id"));
	}
}
