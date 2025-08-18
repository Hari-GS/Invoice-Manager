package com.invoiceapp.invoice_API.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {
    private String invoiceId;
    private String vendorName;
    private String totalAmount;
    private String dueDate;
    private String daysLeft;

}
