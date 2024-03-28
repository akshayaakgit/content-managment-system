package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponse;
import com.example.cms.entity.User;
import com.example.cms.service.BlogService;
import com.example.cms.service.UserService;
import com.example.cms.utility.ErrorStructure;
import com.example.cms.utility.ResponseStructure;

@RestController
@AllArgsConstructor
public class UserController {
	private UserService userService;

	@Operation(description = "user data is saved in database",responses = {
			@ApiResponse(responseCode = "200",description = "user Saved"),
			@ApiResponse(responseCode = "400",description = "Invalid inputs",content= {
					@Content(schema=@Schema(implementation = ErrorStructure.class))
			})
	})
	@PostMapping("/users/register")
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody @Valid UserRequest userRequest){
		return userService.registerUser(userRequest);
		
	}
	@GetMapping("/test")
	public String Test(){
		return "hello from cms";
		
	}
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int userId) {
	return 	userService.deleteUser(userId);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> getUser(@PathVariable int userId) {
	return 	userService.getUser(userId);
	}	
}
