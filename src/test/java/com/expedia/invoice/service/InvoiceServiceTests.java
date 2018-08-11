/**
 * Unit Tests of Invoice service
 * @author Nandakumar.K
 * @date 04-08-2018
 */
package com.expedia.invoice.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.expedia.invoice.constants.InvoiceConstants;
import com.expedia.invoice.entity.Customer;
import com.expedia.invoice.entity.Invoice;
import com.expedia.invoice.repository.CustomerRepository;
import com.expedia.invoice.repository.InvoiceRepository;
import com.expedia.invoice.request.InvoiceRequest;
import com.expedia.invoice.response.InvoiceResponse;

@SpringBootTest
public class InvoiceServiceTests {
	
	@InjectMocks
	private InvoiceService invoiceServiceImpl;
	
	@Mock
	private InvoiceRepository invoiceRepository;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	private static final String CUSTOMER_ID = "121-232-323";
	private static final String INVOICE_ID = "231-3242-3433";
	
	@Test
	public void createInvoice_test() {
		// Create Mock Input Values
		InvoiceRequest invoiceRequest = getMockInvoiceRequest();
		Invoice invoice = getMockInvoice();
		Customer customer = getMockCustomer();
		InvoiceResponse invoiceResponse = getMockInvoiceResponse();
		
		// Mock Database Insert Values
		Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(customer));
		Mockito.when(invoiceRepository.save(invoice)).thenReturn(invoice);
		
		// Assert Response Value
		InvoiceResponse actualResponse = invoiceServiceImpl.createInvoice(invoiceRequest);
		assertEquals(invoiceResponse, actualResponse);
	}
	
	@Test
	public void editInvoice_test() {
		// Create Mock Input Values
		InvoiceRequest invoiceRequest = getMockInvoiceRequest();
		invoiceRequest.setInvoiceId(INVOICE_ID);
		Invoice invoice = getMockInvoice();
		Customer customer = getMockCustomer();
		InvoiceResponse invoiceResponse = getMockInvoiceResponse();

		// Mock Database Insert Values
		Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(customer));
		Mockito.when(invoiceRepository.save(invoice)).thenReturn(invoice);
		Mockito.when(invoiceRepository.findById(INVOICE_ID)).thenReturn(Optional.of(invoice));

		// Assert Response Value
		InvoiceResponse actualResponse = invoiceServiceImpl.editInvoice(invoiceRequest);
		assertEquals(invoiceResponse, actualResponse);
	}

	@Test
	public void getInvoice_test() {
		Invoice invoice = getMockInvoice();
		InvoiceResponse invoiceResponse = getMockInvoiceResponse();
		
		Mockito.when(invoiceRepository.findById(INVOICE_ID)).thenReturn(Optional.of(invoice));
		
		InvoiceResponse actualResponse = invoiceServiceImpl.getInvoice(INVOICE_ID);
		assertEquals(invoiceResponse, actualResponse);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getInvalidInvoice_test() {
		Mockito.when(invoiceRepository.findById(INVOICE_ID)).thenReturn(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createInvoiceWithInvalidCustomer_test() {
		InvoiceRequest invoiceRequest = getMockInvoiceRequest();
		
		Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(null));
		
		invoiceServiceImpl.createInvoice(invoiceRequest);
	}
	
	@Test
	public void createInvoice_with_Non_medical_test() {
		InvoiceRequest invoiceRequest = getMockInvoiceRequest();
		invoiceRequest.setCategory(InvoiceConstants.NON_MEDICAL);
		Invoice invoice = getMockInvoice();
		invoice.setCategory(InvoiceConstants.NON_MEDICAL);
		Customer customer = getMockCustomer();
		InvoiceResponse invoiceResponse = getMockInvoiceResponse();
		invoice.setCategory(InvoiceConstants.NON_MEDICAL);
		
		Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(customer));
		Mockito.when(invoiceRepository.save(invoice)).thenReturn(invoice);
		
		InvoiceResponse actualResponse = invoiceServiceImpl.createInvoice(invoiceRequest);
		assertEquals(invoiceResponse, actualResponse);
	}
	
	@Test
	public void editInvoiceCategoryAndTaxRate_test() {
		// Create Mock Input Values
		InvoiceRequest invoiceRequest = getMockInvoiceRequest();
		invoiceRequest.setInvoiceId(INVOICE_ID);
		invoiceRequest.setCategory("New");
		invoiceRequest.setTaxRate(0.23F);
		Invoice invoice = getMockInvoice();
		Customer customer = getMockCustomer();
		InvoiceResponse invoiceResponse = getMockInvoiceResponse();

		// Mock Database Insert Values
		Mockito.when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(customer));
		Mockito.when(invoiceRepository.save(invoice)).thenReturn(invoice);
		Mockito.when(invoiceRepository.findById(INVOICE_ID)).thenReturn(Optional.of(invoice));

		// Assert Response Value
		InvoiceResponse actualResponse = invoiceServiceImpl.editInvoice(invoiceRequest);
		assertEquals(invoiceResponse, actualResponse);
	}
	
	private InvoiceResponse getMockInvoiceResponse() {
		InvoiceResponse invoiceResponse = new InvoiceResponse();
		invoiceResponse.setCategory(InvoiceConstants.MEDICAL);
		invoiceResponse.setCustomerId(CUSTOMER_ID);
		invoiceResponse.setName("Invoice Test");
		invoiceResponse.setPrice(943.34);
		invoiceResponse.setQuantity(2);
		invoiceResponse.setId(INVOICE_ID);
		return invoiceResponse;
	}

	private Invoice getMockInvoice() {
		Invoice invoice = new Invoice();
		invoice.setCategory(InvoiceConstants.MEDICAL);
		invoice.setCustomer(getMockCustomer());
		invoice.setName("Invoice Test");
		invoice.setPrice(943.34);
		invoice.setQuantity(2);
		invoice.setId(INVOICE_ID);
		return invoice;
	}

	private Customer getMockCustomer() {
		Customer customer = new Customer();
		customer.setId(CUSTOMER_ID);
		customer.setName("Test-Customer");
		return customer;
	}

	private InvoiceRequest getMockInvoiceRequest() {
		InvoiceRequest invoiceRequest = new InvoiceRequest();
		invoiceRequest.setCategory(InvoiceConstants.MEDICAL);
		invoiceRequest.setCustomerId(CUSTOMER_ID);
		invoiceRequest.setName("Invoice Test");
		invoiceRequest.setPrice(943.34);
		invoiceRequest.setQuantity(2);
		return invoiceRequest;
	}
}
