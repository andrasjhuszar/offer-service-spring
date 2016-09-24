package com.ahuszar.offerservice.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahuszar.offerservice.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>
{
}
