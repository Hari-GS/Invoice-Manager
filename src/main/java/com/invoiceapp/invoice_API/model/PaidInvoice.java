package com.invoiceapp.invoice_API.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "paid-invoices")
public class PaidInvoice {

    @Id
    private String id;

    private String invoiceId;
    private String vendorName;
    private String totalAmount;
    private String dueDate;
    private String daysLeft;

    private String userId;
}
