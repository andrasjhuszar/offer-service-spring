package com.ahuszar.offerservice.rest.request;

import java.math.BigDecimal;

public class OfferRequest {

	private String description;

	private BigDecimal price;
	
	private Long product;

	public OfferRequest() {
	}

	public OfferRequest(String description, BigDecimal price, Long product) {
		this.description = description;
		this.price = price;
		this.product = product;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Long getProduct() {
		return product;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setProduct(Long product) {
		this.product = product;
	}
	
	
	
}
