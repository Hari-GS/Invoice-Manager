package com.invoiceapp.invoice_API.service;

import com.invoiceapp.invoice_API.model.Invoice;
import com.invoiceapp.invoice_API.model.User;
import com.invoiceapp.invoice_API.repository.InvoiceRepository;
import com.invoiceapp.invoice_API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserRepository userRepository;

    public Invoice saveInvoice(String email, Invoice invoice){
        //find user by email
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        //attach the userId to the invoice
        invoice.setUserId(user.getEmail());

        //save invoice
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getInvoicesByUser(String email){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
        List<Invoice> invoices = invoiceRepository.findByUserId(user.getEmail());
        return invoices;
    }
}
