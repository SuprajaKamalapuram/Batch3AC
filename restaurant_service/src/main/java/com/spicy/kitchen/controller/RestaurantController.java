package com.spicy.kitchen.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

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
import com.spicy.kitchen.model.Restaurant;
import com.spicy.kitchen.service.RestaurantService;

@RestController
@RequestMapping("/api")

public class RestaurantController {
	private RestaurantService service;
	@Autowired
	
	public RestaurantController(RestaurantService service) {
		super();
		this.service = service;
	}
	
	@ExceptionHandler
	  public ResponseEntity<ErrorMessage> handleError1(IdNotFoundException infe) 
	  {
		  ErrorMessage errorMessage=new ErrorMessage();
		  errorMessage.setMessage(infe.getMessage());
		  errorMessage.setStatus(HttpStatus.NOT_FOUND.value());
		  errorMessage.setErrorTime(LocalDateTime.now());
		  return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
	  }
	@ExceptionHandler
	  public ResponseEntity<ErrorMessage> handleError2(Exception e) 
	  {
		  ErrorMessage errorMessage=new ErrorMessage();
		  errorMessage.setMessage(e.getMessage());
		  errorMessage.setStatus(HttpStatus.BAD_REQUEST.value());
		  errorMessage.setErrorTime(LocalDateTime.now());
		  return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	  }
	
	
	  
    @PostMapping("/restaurant")
	public ResponseEntity<Restaurant> createRestaurant (@Valid @RequestBody Restaurant restaurant)
	{
	    service.createRestaurant(restaurant);
	    return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
		
	}
 
	@GetMapping("/restaurant/{id}")
	public Restaurant getRestaurantById(@PathVariable Integer id){
		 if(service.getRestaurantById(id)==null)
			
			{
				throw new IdNotFoundException("The id is not found : "+id);
			}
			
		return service.getRestaurantById(id);
	}
	@PutMapping("/restaurant/{id}")
	public Restaurant updateRestaurant (@RequestBody Restaurant restaurant,@PathVariable Integer id) {
		Restaurant rest = null;
		if(service.getRestaurantById(id)!=null) {
		 rest = service.createRestaurant(restaurant);
		}
		 else {
			 throw new IdNotFoundException("The id is not found : "+id);
		 } 
		
		return rest;
	}
	@DeleteMapping("/restaurant/{id}")
    public void deleteRestaurantById(@PathVariable Integer id) {
		if(service.getRestaurantById(id)==null)
			
		{
			throw new IdNotFoundException("The id is not found : "+id);
		}
		else {
		service.deleteRestaurantById(id);
		}
	}
}
