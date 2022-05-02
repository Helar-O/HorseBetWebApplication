package com.example.telia.dao;

import com.example.telia.model.User;
import com.example.telia.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {

    private final UserRepository userRepository;

    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User addNewUser(User user) throws Exception {
        if (getAllUsers().stream().filter(u -> u.getUsername().equals(user.getUsername())).findFirst().isEmpty()) {
            userRepository.save(user);
            return user;
        }
        throw new Exception("Username taken");
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public Optional<User> getUser(User user) throws Exception {
        Optional<User> existing = getAllUsers()
                .stream()
                .filter(u -> u.getUsername().equals(user.getUsername()))
                .findFirst();
        if (existing.isPresent() && user.getPassword().equals(existing.get().getPassword())) {
            return existing;
        }
        throw new Exception("Invalid login");
    }
}
