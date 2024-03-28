package com.example.cms.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cms.dto.BlogRequestDto;
import com.example.cms.dto.BlogResponseDto;
import com.example.cms.entity.Blog;
import com.example.cms.exception.TitleNotAvailableException;
import com.example.cms.exception.TopicNotSpecifiedException;
import com.example.cms.exception.UserNotFoundByIdException;
import com.example.cms.repository.BlogRepository;
import com.example.cms.repository.UserRepository;
import com.example.cms.service.BlogService;
import com.example.cms.utility.ResponseStructure;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BlogServiceImpl implements BlogService {
	private BlogRepository blogRepository;
	private UserRepository userRepository;
	private ResponseStructure<BlogResponseDto> responseStructure;

	@Override
	public Boolean ifBlogTitleAvailable(String title) {
		return blogRepository.existsByTitle(title);
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponseDto>> createBlog(BlogRequestDto blogRequestDto,
			Integer userId) {
		return userRepository.findById(userId).map(user -> {
			if (blogRepository.existsByTitle(blogRequestDto.getTitle()))
				throw new TitleNotAvailableException("failed to create blog");
			if (blogRequestDto.getTopics().length < 1)
				throw new TopicNotSpecifiedException("failed to create blog");
			Blog blog = blogRepository.save(mapToBlogEntity(blogRequestDto, new Blog()));
			blog.setUsers(java.util.Arrays.asList(user));

			return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
					.setMessage("Blog Created successfully").setData(mapToBlogResponse(blog)));
		}).orElseThrow(() -> new UserNotFoundByIdException("User not found by id"));

	}

	private BlogResponseDto mapToBlogResponse(Blog blog) {
		return BlogResponseDto.builder().blogId(blog.getBlogId()).title(blog.getTitle()).topics(blog.getTopics())
				.about(blog.getAbout()).build();
	}

	private Blog mapToBlogEntity(BlogRequestDto blogRequestDto, Blog blog) {
		blog.setAbout(blogRequestDto.getAbout());
		blog.setTitle(blogRequestDto.getTitle());
		blog.setTopics(blogRequestDto.getTopics());
		return blog;

	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponseDto>> findBlogsById(int blogId) {
		return blogRepository.findById(blogId).map(blog -> {
			return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
					.setMessage("Blog have been founded Successfully!!").setData(mapToBlogResponse(blog)));
		}).orElseThrow(() -> new UserNotFoundByIdException("Blog not found by id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<BlogResponseDto>> updateBlogsById(BlogRequestDto blogRequestDto,
			int blogId) {
		return blogRepository.findById(blogId).map(blog -> {
			blog.setBlogId(blogId);
			blog.setAbout(blogRequestDto.getAbout());
			blog.setTitle(blogRequestDto.getTitle());
			blog.setTopics(blogRequestDto.getTopics());
			blogRepository.save(mapToBlogEntity(blogRequestDto, new Blog()));
			return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
					.setMessage("Blog have been founded Successfully!!").setData(mapToBlogResponse(blog)));
		}).orElseThrow(() -> new UserNotFoundByIdException("Blog not found by id"));
	}

}
