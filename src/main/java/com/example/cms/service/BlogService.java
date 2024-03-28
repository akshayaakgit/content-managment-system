package com.example.cms.service;

import org.springframework.http.ResponseEntity;

import com.example.cms.dto.BlogRequestDto;
import com.example.cms.dto.BlogResponseDto;
import com.example.cms.utility.ResponseStructure;

public interface BlogService {
	Boolean ifBlogTitleAvailable(String title);

	ResponseEntity<ResponseStructure<BlogResponseDto>> createBlog(BlogRequestDto blogRequestDto ,Integer userId);

	ResponseEntity<ResponseStructure<BlogResponseDto>> findBlogsById(int blogId);

	ResponseEntity<ResponseStructure<BlogResponseDto>> updateBlogsById(BlogRequestDto blogRequestDto ,int blogId);

}
