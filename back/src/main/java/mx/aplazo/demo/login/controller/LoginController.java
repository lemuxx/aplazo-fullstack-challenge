package mx.aplazo.demo.login.controller;

import mx.aplazo.demo.auth.JwtUtil;
import mx.aplazo.demo.login.models.LoginRequest;
import mx.aplazo.demo.login.models.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/auth")
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();
        if ("admin@example.com".equals(loginRequest.getEmail()) && "1234".equals(loginRequest.getPassword())) {
            UUID customerId = UUID.randomUUID();
            String token = jwtUtil.generateToken(customerId);
            loginResponse.setMessage("User logged in successfully");
            loginResponse.setToken(token);
            return ResponseEntity.ok(loginResponse);
        } else {
            loginResponse.setMessage("Invalid credentials");
            loginResponse.setToken("");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponse);
        }
    }

}
