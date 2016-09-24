package com.ahuszar.offerservice.model.entity;

import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ahuszar.offerservice.model.Constants;

@Entity
public class Offer {

	@Id
	@GeneratedValue
	private Long id;

	private String description;

	private BigDecimal price;

	public Currency currency = Constants.CURRENCY_CONSTANT;

	@ManyToOne
	private Product product;

	Offer() {
	}

	public Offer(final Product product, final String description, final BigDecimal price) {
		this.description = description;
		this.product = product;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Product getProduct() {
		return product;
	}
}