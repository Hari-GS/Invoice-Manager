package com.invoiceapp.invoice_API.dto;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class UserDTO {
    @NotBlank(message = "Username is required")
    @Email(message = "Username must be a valid email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
