package com.example.PropertyApp.Service;

import com.example.PropertyApp.Entity.Property;
import com.example.PropertyApp.Repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    // Add Property
    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }

    // Get All Properties
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    // Get Property by ID
    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    // Update Property
    public Property updateProperty(Long id, Property property) {
        if (propertyRepository.existsById(id)) {
            property.setId(id);
            return propertyRepository.save(property);
        }
        return null;
    }

    // Delete Property by ID
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}
