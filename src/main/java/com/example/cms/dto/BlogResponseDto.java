package com.example.cms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BlogResponseDto {
	private long blogId;
	private String title;
	private String[] topics;
	private String about;
}
