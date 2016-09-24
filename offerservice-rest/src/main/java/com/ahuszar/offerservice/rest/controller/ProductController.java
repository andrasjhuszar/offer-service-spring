package com.ahuszar.offerservice.rest.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ahuszar.offerservice.model.entity.Product;
import com.ahuszar.offerservice.model.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductRepository productRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<Product> listProducts() {
		return this.productRepository.findAll();
	}

	@Autowired
	ProductController(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
}
