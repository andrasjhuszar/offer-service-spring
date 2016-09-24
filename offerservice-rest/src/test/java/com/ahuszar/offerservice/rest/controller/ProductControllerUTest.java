package com.ahuszar.offerservice.rest.controller;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ahuszar.offerservice.model.entity.Product;
import com.ahuszar.offerservice.model.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerUTest {

	@Mock
	private ProductRepository productRepository;

	private ProductController testObj;
	
	@Before
	public void setup(){
		testObj = new ProductController(productRepository); 
	}
	
	@Test
	public void listProductsShouldReturnAllProducts(){
		// GIVEN
		Product product1 = new Product("banana");
		Product product2 = new Product("kiwi");
		
		// WHEN 
		Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
		Collection<Product> products = testObj.listProducts();
		
		// THEN
		Assert.assertTrue(products.size() == 2);
		Assert.assertEquals(Arrays.asList(product1, product2), products);
	}
}