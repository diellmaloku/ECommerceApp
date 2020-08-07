package com.example.demo.Services;

import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user)   {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId)   {
        userRepository.deleteById(userId);
    }

    public User updateUser(User user)   {
        Optional<User> optionalUser = userRepository.findById(user.getUserId());

        if (optionalUser.isPresent())   {
            User newUser = optionalUser.get();
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setIdentification(user.getIdentification());
            newUser.setBornDate(user.getBornDate());
            newUser.setUserName(user.getUserName());
            newUser.setEmail(user.getEmail());
            userRepository.save(newUser);
            return newUser;
        } else {
            throw new RuntimeException("User with id " + user.getUserId() + " does not exist!");
        }
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new RuntimeException("User with id " + userId + " does not exist!");
        }
    }
}
