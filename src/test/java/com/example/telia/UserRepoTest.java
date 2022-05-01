package com.example.telia;

import com.example.telia.model.User;
import com.example.telia.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepoTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser() {
        userRepository.save(new User("bob", "pooper"));
        List<User> users = (List<User>) userRepository.findAll();
        assert users.size() == 1;
    }
}
