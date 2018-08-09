package com.expedia.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expedia.invoice.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {

}
