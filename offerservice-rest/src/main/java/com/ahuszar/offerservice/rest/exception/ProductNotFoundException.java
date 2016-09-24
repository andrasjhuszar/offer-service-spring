package com.ahuszar.offerservice.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1292649920936118407L;

	public ProductNotFoundException(Long productId) {
		super("could not find product '" + productId + "'.");
	}
	
}
