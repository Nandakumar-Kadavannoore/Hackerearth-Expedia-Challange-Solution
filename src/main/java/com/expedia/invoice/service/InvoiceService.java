package com.expedia.invoice.service;

import com.expedia.invoice.request.InvoiceRequest;
import com.expedia.invoice.response.InvoiceResponse;

public interface InvoiceService {

	InvoiceResponse createInvoice(InvoiceRequest invoiceRequest);

	InvoiceResponse getInvoice(String invoiceId);

	InvoiceResponse editInvoice(InvoiceRequest invoiceRequest);

}
