package com.example.cms.dto;

import java.util.List;

import com.example.cms.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlogRequestDto {
	private String title;
	private String[] topics;
	private String about;
	private List<User> users;
}
