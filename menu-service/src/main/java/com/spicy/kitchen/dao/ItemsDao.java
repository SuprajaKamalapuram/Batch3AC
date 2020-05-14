package com.spicy.kitchen.dao;

import org.springframework.data.repository.CrudRepository;

import com.spicy.kitchen.model.Items;

public interface ItemsDao extends CrudRepository<Items, Integer> {

}
