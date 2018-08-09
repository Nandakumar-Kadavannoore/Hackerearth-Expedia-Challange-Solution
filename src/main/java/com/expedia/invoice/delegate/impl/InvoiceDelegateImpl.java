package com.expedia.invoice.delegate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.expedia.invoice.delegate.InvoiceDelegate;
import com.expedia.invoice.request.InvoiceRequest;
import com.expedia.invoice.response.InvoiceResponse;
import com.expedia.invoice.service.InvoiceService;

@Component
public class InvoiceDelegateImpl implements InvoiceDelegate {
	
	@Autowired
	private InvoiceService invoiceService;

	@Override
	public InvoiceResponse createInvoice(InvoiceRequest invoiceRequest) {
		return invoiceService.createInvoice(invoiceRequest);
	}

	@Override
	public InvoiceResponse getInvoice(String invoiceId) {
		return invoiceService.getInvoice(invoiceId);
	}

	@Override
	public InvoiceResponse editInvoice(InvoiceRequest invoiceRequest) {
		return invoiceService.editInvoice(invoiceRequest);
	}

}
