package com.spicy.kitchen.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spicy.kitchen.model.Items;
import com.spicy.kitchen.model.Menu;
import com.spicy.kitchen.model.Price;
import com.spicy.kitchen.service.MenuServiceImpl;




@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MenuControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MenuServiceImpl service;
	
	@InjectMocks
	private MenuController controller;
	
	
	@Test
	public void testFindById() throws Exception{
		
		Items i1=new Items(1, "DalMakha", "North-Indian", 3.6, new Price(1, 115.0, 28.0, 18.0, 161.0));		
		Items i2=new Items(2, "Dosa", "South-Indian", 7.0, new Price(2, 135.0, 28.0, 18.0, 181.0));
		
		List<Items> items = new ArrayList<Items>();
		
		items.add(i1);
		items.add(i2);
				
		Menu m=new Menu(items, "3AC");
		
		Mockito.when(service.findById(Mockito.anyString())).thenReturn(Optional.of(m));
		
		mockMvc.perform(get("/spicy/menu/3AB").accept(MediaType.APPLICATION_JSON))
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(jsonPath("$.id", is("3AC")));
		
	}
	
	@Test
	public void testCreateMenu() throws Exception{
		
		Items i1=new Items(1, "DalMakha", "North-Indian", 3.6, new Price(1, 115.0, 28.0, 18.0, 161.0));		
		Items i2=new Items(2, "Dosa", "South-Indian", 7.0, new Price(2, 135.0, 28.0, 18.0, 181.0));
		
		List<Items> items = new ArrayList<Items>();
		
		items.add(i1);
		items.add(i2);
				
		Menu m=new Menu(items, "3AC");
		
		Mockito.when(service.createMenu(m)).thenReturn(m);
		
		ObjectMapper mapper = new ObjectMapper();
		   mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		   ObjectWriter ow =mapper.writer().withDefaultPrettyPrinter();
		   String requestJson=ow.writeValueAsString(m);
		   System.out.println( requestJson);
		   
		   mockMvc.perform(post("/spicy/menu").contentType(MediaType.APPLICATION_JSON)
				   .accept(MediaType.APPLICATION_JSON).content(requestJson))
				   .andDo(print());
	}
	
	@Test
	public void testUpdateMenu() throws Exception{
		
		Items i1=new Items(1, "DalMakha", "North-Indian", 3.6, new Price(1, 115.0, 28.0, 18.0, 161.0));		
		Items i2=new Items(2, "Dosa", "South-Indian", 7.0, new Price(2, 135.0, 28.0, 18.0, 181.0));
		
		i2.setItemName("Poori");
		
		List<Items> items = new ArrayList<Items>();
		
		items.add(i1);
		items.add(i2);
				
		Menu m=new Menu(items, "3AC");
		
		
		Mockito.when(service.createMenu(m)).thenReturn(m);
		
		ObjectMapper mapper = new ObjectMapper();
		   mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		   ObjectWriter ow =mapper.writer().withDefaultPrettyPrinter();
		   String requestJson=ow.writeValueAsString(m);
		   System.out.println( requestJson);
		
		
		mockMvc.perform(put("/spicy/menu/3AC").contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON).content(requestJson))
		         .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		         .andDo(print());
	}
	
	@Test
	public void testDeleteById() throws Exception{
		
		Mockito.doNothing().when(service).deleteById("3AC");
		
		mockMvc.perform(delete("/myrestaurant/menu/3AC")).andReturn();
	}

}