package com.spicy.kitchen.service;


import java.util.Optional;

import com.spicy.kitchen.model.Menu;

public interface MenuService {
	
	public Menu createMenu(Menu menu);
	
	public Optional<Menu> findById(String id);
	void deleteById(String id);
;
}
