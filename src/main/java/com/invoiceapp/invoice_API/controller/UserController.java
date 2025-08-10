package com.invoiceapp.invoice_API.controller;
import com.invoiceapp.invoice_API.dto.AuthResponseDTO;
import com.invoiceapp.invoice_API.model.User;
import com.invoiceapp.invoice_API.dto.UserDTO;
import com.invoiceapp.invoice_API.service.UserService;
import com.invoiceapp.invoice_API.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice-api/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signup(@Valid @RequestBody UserDTO userDTO) {
        User user = userService.signup(userDTO);
        String accessToken = jwtUtil.generateAccessToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponseDTO(accessToken, refreshToken));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody UserDTO userDTO) {
        AuthResponseDTO authResponseDTO = userService.login(userDTO);
        return ResponseEntity.ok(authResponseDTO);
    }

    @PostMapping("/refresh")
    public AuthResponseDTO refresh(@RequestBody String refreshToken) {
        String email = jwtUtil.validateTokenAndGetEmail(refreshToken);
        String newAccessToken = jwtUtil.generateAccessToken(email);
        String newRefreshToken = jwtUtil.generateRefreshToken(email);  // Optional: Rotate refresh token
        return new AuthResponseDTO(newAccessToken, newRefreshToken);
    }


}
