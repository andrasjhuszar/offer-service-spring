package com.ahuszar.offerservice.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahuszar.offerservice.model.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}