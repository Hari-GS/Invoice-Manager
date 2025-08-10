package com.invoiceapp.invoice_API.exception;

import com.invoiceapp.invoice_API.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceConflict(ResourceConflictException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(ex.getMessage(), 409,System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // Optional: handle validation errors globally too
}
