package com.spicy.kitchen.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="menu")
public class Menu {

	@Id
	private final String id="3AC";
	
	@OneToMany(targetEntity = Items.class, cascade= CascadeType.ALL)
	@JoinColumn(name="mi_fk", referencedColumnName = "id")
	private List<Items> items;

	

	public String getId() {
		return id;
	}


	public Menu() {
		super();
	}


	public Menu(List<Items> items,String id) {
		super();
		this.items = items;
		
	}


	@Override
	public String toString() {
		return "Menu [id=" + id + ", items=" + items + "]";
	}


	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}
	
}
	

	

