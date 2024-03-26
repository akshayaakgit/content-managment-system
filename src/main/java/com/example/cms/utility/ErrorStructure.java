package com.example.cms.utility;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class ErrorStructure<T> {
	private int statuscode;
	private T rootCause;
	private String message;

	public ErrorStructure<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public ErrorStructure<T> setStatuscode(int statuscode) {
		this.statuscode = statuscode;
		return this;
	}

	public ErrorStructure<T> setRootCause(T rootCause) {
		this.rootCause = rootCause;
		return this;
	}

}
