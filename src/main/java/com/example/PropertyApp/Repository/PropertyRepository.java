package com.example.PropertyApp.Repository;

import com.example.PropertyApp.Entity.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
//    Page<Property> findByUserId(Long userId, Pageable pageable);
}
