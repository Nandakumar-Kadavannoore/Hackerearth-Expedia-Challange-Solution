/**
 * Request class for Invoice
 * @author Nandakumar.K
 * @date 04-08-2018
 */
package com.expedia.invoice.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InvoiceRequest {
	
	private String invoiceId;
	
	@NotNull
	@NotEmpty
	private String customerId;
	
	@NotNull
	@NotEmpty
	private String name;
	
	@NotNull
	@NotEmpty
	private int quantity;
	
	@NotNull
	@NotEmpty
	private String category;
	
	@NotNull
	@NotEmpty
	private double price;
	
	@NotNull
	@NotEmpty
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	

}
