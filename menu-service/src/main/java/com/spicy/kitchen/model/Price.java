package com.spicy.kitchen.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Price  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer pId;
	private double basePrice;
	private double cgst;
	private double sgst;
	private double totalPrice;
	
	public Price() {
		super();
	}
	public Price(Integer pId,double basePrice,  double cgst, double sgst, double totalPrice) {
		super();
		this.pId = pId;
		this.basePrice = basePrice;
		this.cgst = cgst;
		this.sgst = sgst;
		this.totalPrice = totalPrice;
	}
	
	
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public double getCgst() {
		return cgst;
	}
	public void setCgst(double cgst) {
		this.cgst = cgst;
	}
	public double getSgst() {
		return sgst;
	}
	public void setSgst(double sgst) {
		this.sgst = sgst;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

}
