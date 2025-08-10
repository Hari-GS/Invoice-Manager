package com.invoiceapp.invoice_API.service;

import com.invoiceapp.invoice_API.dto.AuthResponseDTO;
import com.invoiceapp.invoice_API.exception.ResourceConflictException;
import com.invoiceapp.invoice_API.model.User;
import com.invoiceapp.invoice_API.dto.UserDTO;
import com.invoiceapp.invoice_API.repository.UserRepository;
import com.invoiceapp.invoice_API.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User signup(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new ResourceConflictException("Username already exists");
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    public AuthResponseDTO login(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            String accessToken = jwtUtil.generateAccessToken(user.getEmail());
            String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());
            return new AuthResponseDTO(accessToken, refreshToken);
        } else {
            throw new RuntimeException("Invalid password");
        }
    }

}
