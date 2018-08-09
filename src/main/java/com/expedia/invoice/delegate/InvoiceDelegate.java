package com.expedia.invoice.delegate;

import com.expedia.invoice.request.InvoiceRequest;
import com.expedia.invoice.response.InvoiceResponse;

public interface InvoiceDelegate {

	InvoiceResponse createInvoice(InvoiceRequest invoiceRequest);

	InvoiceResponse getInvoice(String invoiceId);

	InvoiceResponse editInvoice(InvoiceRequest invoiceRequest);

}
