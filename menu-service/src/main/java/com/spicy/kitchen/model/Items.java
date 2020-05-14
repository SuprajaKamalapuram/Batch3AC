package com.spicy.kitchen.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itemId;
	private String itemName;
	private String description;
	private double rating;
	@OneToOne(targetEntity = Price.class, cascade = CascadeType.ALL)
	@JoinColumn(name= "p_fk", referencedColumnName = "pId")
	private Price price;
	public Items() {
		super();
	}
	
	public Items(Integer itemId, String itemName, String description, double rating, Price price) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.description = description;
		this.rating = rating;
		this.price = price;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Items [itemId=" + itemId + ", itemName=" + itemName + ", description=" + description + ", rating=" + rating
				+ ", price=" + price + "]";
	}
	
	
	
	
	
}