package com.ahuszar.offerservice.rest.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ahuszar.offerservice.rest.exception.ProductNotFoundException;
import com.ahuszar.offerservice.rest.request.OfferRequest;
import com.ahuszar.offerservice.model.entity.Offer;
import com.ahuszar.offerservice.model.entity.Product;
import com.ahuszar.offerservice.model.repository.OfferRepository;
import com.ahuszar.offerservice.model.repository.ProductRepository;

@RestController
@RequestMapping("/offers")
public class OfferController {

	private final OfferRepository offerRepository;
	private final ProductRepository productRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<Offer> listOffers() {
		return this.offerRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	Offer create(@RequestBody OfferRequest request) {
		Product product = productRepository.findOne(request.getProduct());
		if (product == null) {
			throw new ProductNotFoundException(request.getProduct());
		}

		Offer offer = offerRepository.save(new Offer(product, request.getDescription(), request.getPrice()));
		return offer;
	}

	@Autowired
	OfferController(final OfferRepository offerRepository, final ProductRepository productRepository) {
		this.offerRepository = offerRepository;
		this.productRepository = productRepository;
	}
}
