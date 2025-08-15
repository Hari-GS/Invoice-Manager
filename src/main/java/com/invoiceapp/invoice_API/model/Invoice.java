package com.invoiceapp.invoice_API.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "invoices")
public class Invoice {
    @Id
    private String id;

    private String invoiceId;
    private String vendorName;
    private String totalAmount;
    private String dueDate;
    private String daysLeft;

    private String userId;
}
