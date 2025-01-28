package com.example.PropertyApp.Controller;

import com.example.PropertyApp.Entity.Property;
import com.example.PropertyApp.Service.PropertyService;
import com.example.PropertyApp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private JwtUtil jwtUtil;  // JWT Utility for token verification

    // Add Property - JWT authentication required
    @PostMapping("/add")
    public ResponseEntity<Property> addProperty(@RequestBody Property property, HttpServletRequest request) {
        if (!isAuthenticated(request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Property savedProperty = propertyService.addProperty(property);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProperty);
    }

    // Get All Properties - JWT authentication required
    @GetMapping("/")
    public ResponseEntity<List<Property>> getAllProperties(HttpServletRequest request) {
        if (!isAuthenticated(request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Property> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    // Get Property by ID - JWT authentication required
    @GetMapping("/{id}")
    public ResponseEntity<Property> getProperty(@PathVariable Long id, HttpServletRequest request) {
        if (!isAuthenticated(request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Property> propertyOptional = propertyService.getPropertyById(id);
        if (propertyOptional.isPresent()) {
            return ResponseEntity.ok(propertyOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Update Property by ID - JWT authentication required
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property property, HttpServletRequest request) {
        if (!isAuthenticated(request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Property updatedProperty = propertyService.updateProperty(id, property);
        if (updatedProperty != null) {
            return ResponseEntity.ok(updatedProperty);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Delete Property by ID - JWT authentication required
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id, HttpServletRequest request) {
        if (!isAuthenticated(request)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Property> property = propertyService.getPropertyById(id);
        if (property.isPresent()) {
            propertyService.deleteProperty(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Helper method to check authentication based on JWT token
    private boolean isAuthenticated(HttpServletRequest request) {
        String token = getTokenFromRequest(request);
        return token != null && Boolean.TRUE.equals(jwtUtil.isTokenValid(token));
    }

    // Helper method to extract the token from the Authorization header
    private String getTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7); // Extract token part
        }
        return null;
    }
}
