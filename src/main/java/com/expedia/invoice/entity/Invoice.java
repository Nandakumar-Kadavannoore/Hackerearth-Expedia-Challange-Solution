package com.expedia.invoice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	
    private Customer customer;
	
	private String name;
	
	private int quantity;
	
	private String category;
	
	private double price;
	
	private float taxRate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public float getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(float taxRate) {
		this.taxRate = taxRate;
	} 
	
}
