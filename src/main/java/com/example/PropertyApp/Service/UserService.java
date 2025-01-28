package com.example.PropertyApp.Service;

import com.example.PropertyApp.Entity.User;
import com.example.PropertyApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

//public class UserService {
//}
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signup(User user) {
        return userRepository.save(user); // Sign up logic
    }

//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username).orElse(null); // Fetch user by username
//    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);  // Assuming you are using Spring Data JPA
    }


    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(User user) {
        return userRepository.save(user);  // This will update the user in the database
    }

//    public void deleteUser(Long id) {
//        userRepository.deleteById(id); // Delete user by ID
//    }

    public void deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }
    }

}

