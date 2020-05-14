package com.spicy.kitchen.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spicy.kitchen.dao.ItemsDao;
import com.spicy.kitchen.dao.MenuDao;
import com.spicy.kitchen.dao.PriceDao;
import com.spicy.kitchen.model.Menu;

@Service
public class MenuServiceImpl implements MenuService {
	
	private MenuDao menuDao;
	private ItemsDao menuItemsDao;
	private PriceDao priceDao;
	
	@Autowired
	public MenuServiceImpl(MenuDao menuDao, ItemsDao menuItemsDao, PriceDao priceDao) {
		super();
		this.menuDao = menuDao;
		this.menuItemsDao=menuItemsDao;
		this.priceDao=priceDao;
		
	}

	@Override
	public Menu createMenu(Menu menu) {
		// TODO Auto-generated method stub
		return menuDao.save(menu);
	}

	@Override
	public Optional<Menu> findById(String id) {
		// TODO Auto-generated method stub
		return menuDao.findById(id);
	}

	

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		menuDao.deleteById(id);
		
	}
}
