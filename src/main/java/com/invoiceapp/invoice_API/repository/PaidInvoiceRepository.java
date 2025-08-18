package com.invoiceapp.invoice_API.repository;

import com.invoiceapp.invoice_API.model.PaidInvoice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaidInvoiceRepository extends MongoRepository<PaidInvoice, String> {
    List<PaidInvoice> findByUserId(String userId);
}
