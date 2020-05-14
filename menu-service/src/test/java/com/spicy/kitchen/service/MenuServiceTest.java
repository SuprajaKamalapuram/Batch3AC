package com.spicy.kitchen.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spicy.kitchen.dao.MenuDao;
import com.spicy.kitchen.model.Items;
import com.spicy.kitchen.model.Menu;
import com.spicy.kitchen.model.Price;



@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceTest {
	
	@Mock
	private MenuDao menuDao;
	
	@InjectMocks
	private MenuServiceImpl menuService;

	
	@Test
	public void testCreateMenu() throws Exception {
		
		Items i1=new Items(1, "DalMakha", "North-Indian", 3.6, new Price(1, 115.0, 28.0, 18.0, 161.0));		
		Items i2=new Items(2, "Dosa", "South-Indian", 7.0, new Price(2, 135.0, 28.0, 18.0, 181.0));
		
		List<Items> items = new ArrayList<Items>();
		
		items.add(i1);
		items.add(i2);
				
		Menu m=new Menu(items,"3AC");
		
		Mockito.when(menuDao.save(m)).thenReturn(m);		
		assertThat(menuService.createMenu(m)).isEqualTo(m);
		
	}
	
	@Test
	public void testFindById() {
		
		Items i1=new Items(1, "DalMakha", "North-Indian", 3.6, new Price(1, 115.0, 28.0, 18.0, 161.0));		
		Items i2=new Items(2, "Dosa", "South-Indian", 7.0, new Price(2, 135.0, 28.0, 18.0, 181.0));
		
		List<Items> items = new ArrayList<Items>();
		
		items.add(i1);
		items.add(i2);
				
		Menu m=new Menu(items,"3AC");
		
		Mockito.when(menuDao.findById(Mockito.anyString())).thenReturn(Optional.of(m));
		menuService.findById("3AC");
		
	}
	@Test
	public void testDeleteById() {
		
	}
}
