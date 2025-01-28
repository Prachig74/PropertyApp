package com.example.PropertyApp.Entity;

import jakarta.persistence.*;
import lombok.Data;

    @Entity
    @Data // Lombok annotation for getters, setters, etc.
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(unique = true)
        private String username;
        private String password;
//        private String email;

        public Long getId() {
            return id;
        }

        // Optionally, you can add a setter if needed
        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


    }


