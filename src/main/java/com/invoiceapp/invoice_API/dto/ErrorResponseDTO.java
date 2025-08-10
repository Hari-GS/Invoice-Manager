package com.invoiceapp.invoice_API.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {
    private String message;
    private int status;
    private long timestamp;
}
