package com.ahuszar.offerservice.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private Long id;

	public String name;

	public Product(final String name) {
		this.name = name;
	}

	Product() {
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}