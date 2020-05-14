package com.spicy.kitchen.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spicy.kitchen.exception.ErrorMessage;
import com.spicy.kitchen.exception.IdNotFoundException;
import com.spicy.kitchen.model.Menu;
import com.spicy.kitchen.service.MenuService;

@RestController
@RequestMapping("/spicy")
public class MenuController {
	
	private MenuService menuService;

	@Autowired
	public MenuController(MenuService menuService) {
		super();
		this.menuService = menuService;
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorMessage> handleError1(IdNotFoundException ine)
	{
		ErrorMessage errorMessage=new ErrorMessage();
		errorMessage.setMessage(ine.getMessage());
		errorMessage.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorMessage.setErrorTime(LocalDateTime.now());
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler
	public ResponseEntity<ErrorMessage> handleError2(Exception e)
	{
		ErrorMessage errorMessage=new ErrorMessage();
		errorMessage.setMessage(e.getMessage());
		errorMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
		errorMessage.setErrorTime(LocalDateTime.now());
		return new ResponseEntity<ErrorMessage>(errorMessage,HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/menu")
	public Menu createMenu(@RequestBody Menu menu) {
		// TODO Auto-generated method stub
		return menuService.createMenu(menu);
	}

	@GetMapping("/menu/{id}")
	public Optional<Menu> findById(@PathVariable String id) throws Exception {
		// TODO Auto-generated method stub
		Optional<Menu> menu1=menuService.findById(id);
		if(!menu1.isPresent())
		{
			throw new IdNotFoundException("Item not found with id: "+id);
		}
		return menuService.findById(id);
	}
	
	
	
	@DeleteMapping("/menu/{id}")
	public void deleteById(@PathVariable String id) {
		if(menuService.findById(id)!=null) {
		menuService.deleteById(id);
		}
	else {
		throw new IdNotFoundException("Item not found with id: "+id);
	}
	}
	
	@PutMapping("/menu/{id}")
	public Menu updateMenuById(@RequestBody Menu menu, @PathVariable String id) {
	if(menuService.findById(id)!=null) {
       menuService.createMenu(menu);
	}
	else  {
			throw new IdNotFoundException("Item not found with id: "+id);
		}
	return menu;
	}
	
	

}
