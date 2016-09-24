package com.ahuszar.offerservice.rest;

import java.math.BigDecimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ahuszar.offerservice.model.entity.Offer;
import com.ahuszar.offerservice.model.entity.Product;
import com.ahuszar.offerservice.model.repository.OfferRepository;
import com.ahuszar.offerservice.model.repository.ProductRepository;

@Configuration
@ComponentScan
@EntityScan("com.ahuszar.offerservice.model.entity")
@EnableAutoConfiguration
@EnableJpaRepositories("com.ahuszar.offerservice.model.repository")
public class Application {

	@Bean
	boolean init(final ProductRepository productRepository, final OfferRepository offerRepository) {
		initTestData(productRepository, offerRepository);
		return true;
	}

	private void initTestData(final ProductRepository productRepository, final OfferRepository offerRepository) {
		final Product product = productRepository.save(new Product("banana"));
		productRepository.save(new Product("orange"));
		productRepository.save(new Product("apple"));
		productRepository.save(new Product("lemon"));

		offerRepository.save(new Offer(product, "Banana summer sale", BigDecimal.valueOf(2.50)));
	}

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}
}