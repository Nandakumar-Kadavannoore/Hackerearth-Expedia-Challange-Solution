/**
 * Service implementation of Invoice Service
 * @author Nandakumar.K
 * @date 04-08-2018
 */
package com.expedia.invoice.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.expedia.invoice.entity.Customer;
import com.expedia.invoice.entity.Invoice;
import com.expedia.invoice.helper.InvoiceHelper;
import com.expedia.invoice.repository.CustomerRepository;
import com.expedia.invoice.repository.InvoiceRepository;
import com.expedia.invoice.request.InvoiceRequest;
import com.expedia.invoice.response.InvoiceResponse;
import com.expedia.invoice.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public InvoiceResponse createInvoice(InvoiceRequest invoiceRequest) {
		// Check If Customer Exist if true get else throw exception
		Customer customer = checkIfCustomerExist(invoiceRequest.getCustomerId());
		// Save Changes to Invoice database
		Invoice invoice = saveInvoiceToDatabase(invoiceRequest, customer, false);
		// Convert Invoice into InvoiceResponse and return
		return getInvoiceResponse(invoice);
	}

	@Override
	public InvoiceResponse getInvoice(String invoiceId) {
		// Get Invoice from database by Invoice Id
		Invoice invoice = getInvoiceFromDatabase(invoiceId);
		// Convert Invoice into Invoice Response
		return InvoiceHelper.convertInvoiceIntoInvoiceResponse.apply(invoice);
	}
	
	@Override
	public InvoiceResponse editInvoice(InvoiceRequest invoiceRequest) {
		// If Invoice Id is not empty update else do nothing
		if (!StringUtils.isEmpty(invoiceRequest.getInvoiceId())) {
			// Check if request Invoice Exist or not else throw exception
			getInvoiceFromDatabase(invoiceRequest.getInvoiceId());
			// Check If Customer Exist if true get else throw exception
			Customer customer = checkIfCustomerExist(invoiceRequest.getCustomerId());
			// Save Changes to Invoice database
			Invoice invoice = saveInvoiceToDatabase(invoiceRequest, customer, true);
			// Convert Invoice into InvoiceResponse and return
			return getInvoiceResponse(invoice);
		}
		return new InvoiceResponse();
	}

	/**
	 * Helper Method to get Invoice from Database
	 * @param invoiceId
	 * @return Invoice
	 */
	private Invoice getInvoiceFromDatabase(String invoiceId) {
		return invoiceRepository.findById(invoiceId)
				.orElseThrow(() -> new IllegalArgumentException("No invoice found with id " + invoiceId));
	}
	
	/**
	 * Helper Method for Convert Invoice into InvoiceResponse
	 * @param invoice
	 * @return InvoiceResponse
	 */
	private InvoiceResponse getInvoiceResponse(Invoice invoice) {
		return InvoiceHelper.convertInvoiceIntoInvoiceResponse.apply(invoice);
	}

	/**
	 * Helper Method for Save Invoice into database
	 * @param invoiceRequest
	 * @param customer
	 * @return Invoice
	 */
	private Invoice saveInvoiceToDatabase(InvoiceRequest invoiceRequest, Customer customer, boolean isExist) {
		Invoice invoice = InvoiceHelper.convertInvoiceRequestIntoInvoice.apply(invoiceRequest);
		invoice.setCustomer(customer);
		// If Invoice already exist in database update and use previous id itself
		if (isExist) {
			invoice.setId(invoiceRequest.getInvoiceId());
		}
		return invoiceRepository.save(invoice);
	}

    /**
     * Helper Method for Check if Customer exist in database by customer Id
     * @param customerId
     * @return Customer
     */
	private Customer checkIfCustomerExist(String customerId) {
		Optional<Customer> customerOptional = customerRepository.findById(customerId);
		return customerOptional
				.orElseThrow(() -> new IllegalArgumentException("No Customer found with id " + customerId));
	}

	

}
