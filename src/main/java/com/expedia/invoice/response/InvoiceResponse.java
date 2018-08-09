/**
 * Response class for invoice
 * @author Nandakumar.K
 * @date 04-08-2018
 */
package com.expedia.invoice.response;

public class InvoiceResponse {
	
	private String id;
	
	private String customerId;
	
    private String name;
	
	private int quantity;
	
	private String category;
	
	private double price;
	
	private float taxRate;

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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
    
}
