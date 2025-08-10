package com.invoiceapp.invoice_API.repository;

import com.invoiceapp.invoice_API.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String username);
}