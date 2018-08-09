/**
 * Invoice Controller
 * @author Nandakumar.K
 * @date 04-08-2018
 */
package com.expedia.invoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expedia.invoice.delegate.InvoiceDelegate;
import com.expedia.invoice.request.InvoiceRequest;
import com.expedia.invoice.response.InvoiceResponse;

@RestController
@RequestMapping("/invoice")
@CrossOrigin
public class InvoiceController {
	
	@Autowired
	private InvoiceDelegate invoiceDelegate;
	
	@PostMapping
	public ResponseEntity<InvoiceResponse> createInvoice(
			@Validated @RequestBody InvoiceRequest invoiceRequest) {
		InvoiceResponse invoiceResponse = invoiceDelegate.createInvoice(invoiceRequest);
		return new ResponseEntity<>(invoiceResponse, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<InvoiceResponse> editInvoice(
			@Validated @RequestBody InvoiceRequest invoiceRequest) {
		InvoiceResponse invoiceResponse = invoiceDelegate.editInvoice(invoiceRequest);
		return new ResponseEntity<>(invoiceResponse, HttpStatus.CREATED);
	} 
	
	@GetMapping("/{invoiceId}")
	public ResponseEntity<InvoiceResponse> getInvoice(
			@PathVariable(value = "invoiceId")String invoiceId) {
		InvoiceResponse invoiceResponse = invoiceDelegate.getInvoice(invoiceId);
		return new ResponseEntity<>(invoiceResponse, HttpStatus.CREATED);
	}

}
