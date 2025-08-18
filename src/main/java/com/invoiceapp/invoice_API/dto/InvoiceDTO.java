package com.invoiceapp.invoice_API.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceDTO {
    private String id;          // MongoDB id
    private String invoiceId;
    private String vendorName;
    private String totalAmount;
    private String dueDate;
    private String daysLeft;

}
