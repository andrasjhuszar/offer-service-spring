package com.ahuszar.offerservice.rest.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ahuszar.offerservice.rest.exception.ProductNotFoundException;
import com.ahuszar.offerservice.rest.request.OfferRequest;
import com.ahuszar.offerservice.model.entity.Offer;
import com.ahuszar.offerservice.model.entity.Product;
import com.ahuszar.offerservice.model.repository.OfferRepository;
import com.ahuszar.offerservice.model.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class OfferControllerUTest {

	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private OfferRepository offerRepository;
	
	private OfferController testObj;
	
	@Before
	public void setup(){
		testObj = new OfferController(offerRepository, productRepository); 
	}
	
	@Test
	public void listOffersShouldReturnAllOffers(){
		// GIVEN
		Product product1 = new Product("banana");
		Product product2 = new Product("kiwi");
		
		Offer offer1 = new Offer(product1, "offer1", BigDecimal.valueOf(12.34));
		Offer offer2 = new Offer(product2, "offer2",  BigDecimal.valueOf(13.24));
		
		// WHEN 
		Mockito.when(offerRepository.findAll()).thenReturn(Arrays.asList(offer1, offer2));
		Collection<Offer> offers = testObj.listOffers();
		
		// THEN
		Assert.assertTrue(offers.size() == 2);
		Assert.assertEquals(Arrays.asList(offer1, offer2), offers);
	}
	
	@Test
	public void createShouldSaveAndReturnTheOffer(){
		// GIVEN
		BigDecimal price = BigDecimal.valueOf(11.1);
		Long productId = 1l;
		String description = "description";

		OfferRequest request = new OfferRequest(description, price, productId);
		
		Product banana = new Product("banana");
		Offer offer = new Offer(banana, description, price);
		
		// WHEN 
		Mockito.when(productRepository.findOne(productId)).thenReturn(banana);
		Mockito.when(offerRepository.save(Mockito.any(Offer.class))).thenReturn(offer);
		Offer offerResponse = testObj.create(request);
		
		// THEN
		Mockito.verify(offerRepository, Mockito.only()).save(Mockito.any(Offer.class));
		Assert.assertEquals(offer, offerResponse);
	}
	
	@Test(expected=ProductNotFoundException.class)
	public void createShouldThrowExceptionIfProductIsNotAvailable(){

		// GIVEN
		BigDecimal price = BigDecimal.valueOf(11.1);
		Long productId = 1l;
		String description = "description";

		OfferRequest request = new OfferRequest(description, price, productId);
		
		// WHEN 
		Mockito.when(productRepository.findOne(productId)).thenReturn(null);
		testObj.create(request);
		
	}
}
