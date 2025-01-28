



package com.example.PropertyApp.Controller;

import com.example.PropertyApp.Entity.User;
import com.example.PropertyApp.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import com.example.PropertyApp.util.JwtUtil;
// Ensure JwtUtil class exists and contains the method 'generateToken(String username)'
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;







        @Autowired






//    @Autowired
    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }



    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        user.setPassword(encodePassword(user.getPassword())); // Encrypt password before saving
        User savedUser = userService.signup(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<String> validateToken(HttpServletRequest request) {
        // Extract token from Authorization header
        String token = getTokenFromRequest(request);

        if (token == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token is missing!");
        }

        // Validate the token
        boolean isValid = jwtUtil.isTokenValid(token);
        if (isValid) {
            return ResponseEntity.ok("Token is valid!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid!");
        }
    }

    // Helper method to extract the token from the request header
    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // Extract token part
        }
        return null; // No token provided
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User user) {
//        User existingUser = userService.findByUsername(user.getUsername()).orElse(null);
//        if (existingUser != null && checkPassword(user.getPassword(), existingUser.getPassword())) {
//            return ResponseEntity.ok("Login successful");
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//    }



//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User user) {
//        System.out.println("Login attempt for username: " + user.getUsername());
//        User existingUser = userService.findByUsername(user.getUsername()).orElse(null);
//        if (existingUser != null && checkPassword(user.getPassword(), existingUser.getPassword())) {
//            return ResponseEntity.ok("Login successful");
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        User existingUser = userService.findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!checkPassword(user.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Invalid credentials"));
        }

        String token = jwtUtil.generateToken(existingUser.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);  // Return the token here
        return ResponseEntity.ok(response);  // Send the token in the response
    }



    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password); // Use BCrypt for password hashing
    }

    private boolean checkPassword(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }
}

