package com.example.cms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cms.dto.BlogRequestDto;
import com.example.cms.dto.BlogResponseDto;
import com.example.cms.dto.UserRequest;
import com.example.cms.dto.UserResponse;
import com.example.cms.service.BlogService;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class BlogController {
	private BlogService blogService;

	@PostMapping("/users/{userId}/blogs")
	public ResponseEntity<ResponseStructure<BlogResponseDto>> createBlog(
			@RequestBody @Valid BlogRequestDto blogRequestDto, @PathVariable Integer userId) {
		return blogService.createBlog(blogRequestDto,userId);

	}

	@GetMapping("/titles/{title}/blogs")
	public Boolean ifBlogTitleAvailable(@PathVariable String title) {
		return blogService.ifBlogTitleAvailable(title);
	}
	
	@GetMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponseDto>> findBlogsById(@PathVariable int blogId) {
		return blogService.findBlogsById(blogId);
	}
	@PutMapping("/blogs/{blogId}")
	public ResponseEntity<ResponseStructure<BlogResponseDto>> updateBlogsById(@RequestBody @Valid BlogRequestDto blogRequestDto,
			@PathVariable int blogId) {
		return blogService.updateBlogsById(blogRequestDto,blogId);
	}

}
