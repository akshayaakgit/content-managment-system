package com.example.cms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
	private String username;
	private String email;
	private String password;
}
