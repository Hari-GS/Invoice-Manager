package com.invoiceapp.invoice_API.service;

import com.invoiceapp.invoice_API.dto.InvoiceDTO;
import com.invoiceapp.invoice_API.mapper.InvoiceMapper;
import com.invoiceapp.invoice_API.model.Invoice;
import com.invoiceapp.invoice_API.model.PaidInvoice;
import com.invoiceapp.invoice_API.model.User;
import com.invoiceapp.invoice_API.repository.InvoiceRepository;
import com.invoiceapp.invoice_API.repository.PaidInvoiceRepository;
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

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    PaidInvoiceRepository paidInvoiceRepository;

    public InvoiceDTO saveInvoice(String email, InvoiceDTO invoiceDTO){
        //find user by email
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        Invoice invoice = invoiceMapper.toEntity(invoiceDTO);
        invoice.setUserId(user.getEmail());

        //save invoice
        invoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDto(invoice);
    }

    public List<InvoiceDTO> getInvoicesByUser(String email){
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
        List<Invoice> invoices = invoiceRepository.findByUserId(user.getEmail());
        return invoiceMapper.toDtoList(invoices);
    }

    //Remove Invoice
    public void removeInvoice(String invoiceId){
        if(!invoiceRepository.existsById(invoiceId)){
            throw new RuntimeException("Invoice not found");
        }
        invoiceRepository.deleteById(invoiceId);
    }

    //Move invoice to PaidInvoices
    public void moveInvoiceToPaid(String invoiceId){
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(()->new RuntimeException("Invoice not found"));

        //Create PaidInvoice from Invoice
        PaidInvoice paidInvoice = new PaidInvoice(null,invoice.getInvoiceId(),invoice.getVendorName(), invoice.getTotalAmount(), invoice.getDueDate(), invoice.getDaysLeft(),invoice.getUserId());

        //save into PaidInvoices collection
        paidInvoiceRepository.save(paidInvoice);

        //delete from invoices collection
        invoiceRepository.delete(invoice);
    }
}
