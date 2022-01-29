package com.techmarket.controller.exceptions;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class ApiValidationErrorsReturn {
	
	private List<String> errors;
	
	public ApiValidationErrorsReturn(List<String> errors) {
		this.errors = errors;
	}
	
	public ApiValidationErrorsReturn(String error) {
		this.errors = Arrays.asList(error);
	}
}
