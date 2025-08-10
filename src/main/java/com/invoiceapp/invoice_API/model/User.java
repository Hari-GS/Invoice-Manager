package com.invoiceapp.invoice_API.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")   // MongoDB collection name
public class User {

    @Id
    private String id;            // MongoDB stores id as string (ObjectId)

    private String email;      // email or phone
    private String password;      // encrypted password
}