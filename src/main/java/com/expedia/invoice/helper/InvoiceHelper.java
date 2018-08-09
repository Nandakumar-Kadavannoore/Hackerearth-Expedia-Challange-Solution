/**
 * Helper Class for Invoice
 * @author Nandakumar.K
 */
package com.expedia.invoice.helper;

import java.util.function.Function;

import com.expedia.invoice.constants.InvoiceConstants;
import com.expedia.invoice.entity.Invoice;
import com.expedia.invoice.request.InvoiceRequest;
import com.expedia.invoice.response.InvoiceResponse;

public class InvoiceHelper {

	private InvoiceHelper() {

	}

	public static Function<Invoice, InvoiceResponse> convertInvoiceIntoInvoiceResponse = (invoice) -> {
			InvoiceResponse invoiceResponse = new InvoiceResponse();
			invoiceResponse.setCustomerId(invoice.getCustomer().getId());
			invoiceResponse.setName(invoice.getName());
			invoiceResponse.setPrice(invoice.getPrice());
			invoiceResponse.setQuantity(invoice.getQuantity());
			invoiceResponse.setTaxRate(invoice.getTaxRate());
			return invoiceResponse;
	};

	public static Function<InvoiceRequest, Invoice> convertInvoiceRequestIntoInvoice = (invoiceRequest) -> {
			Invoice invoice = new Invoice();
			invoice.setName(invoiceRequest.getName());
			invoice.setPrice(invoiceRequest.getPrice());
			invoice.setQuantity(invoiceRequest.getQuantity());
			invoice.setTaxRate(invoiceRequest.getTaxRate());
			// Used for Set Category And Tax rate
			setInvoiceCategoryAndTaxrate(invoice, invoiceRequest);
			return invoice;
	};
	
	private static void setInvoiceCategoryAndTaxrate(Invoice invoice, InvoiceRequest invoiceRequest) {
		switch (invoiceRequest.getCategory().toUpperCase()) {
		case InvoiceConstants.MEDICAL:
			invoice.setCategory(InvoiceConstants.MEDICAL);
			invoice.setTaxRate(InvoiceConstants.MEDICAL_TAX_RATE);
			break;
		case InvoiceConstants.NON_MEDICAL:
			invoice.setCategory(InvoiceConstants.NON_MEDICAL);
			invoice.setTaxRate(InvoiceConstants.MEDICAL_TAX_RATE);
			break;
		default:
			invoice.setCategory(invoiceRequest.getCategory());
			invoice.setTaxRate(invoiceRequest.getTaxRate());
		}
	}

}
