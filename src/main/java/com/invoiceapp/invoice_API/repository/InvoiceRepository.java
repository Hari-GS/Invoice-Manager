package com.invoiceapp.invoice_API.repository;

import com.invoiceapp.invoice_API.model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InvoiceRepository extends MongoRepository<Invoice,String> {
    List<Invoice> findByUserId(String userId);
}
