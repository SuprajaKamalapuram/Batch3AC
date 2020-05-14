package com.spicy.kitchen.dao;

import org.springframework.data.repository.CrudRepository;

import com.spicy.kitchen.model.Price;

public interface PriceDao extends CrudRepository<Price, Integer> {

}
