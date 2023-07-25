package com.example.demo_spring_with_mongo;

import com.example.demo_spring_with_mongo.models.User;
import com.example.demo_spring_with_mongo.repositories.UserRepository;
import com.example.demo_spring_with_mongo.services.UserDALImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserDALImpl userServiceImpl;
    private final UserRepository userRepository;

    public ConsoleRunner(UserDALImpl userServiceImpl, UserRepository userRepository) {
        this.userServiceImpl = userServiceImpl;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Save a new user
        User user = new User("K19990000");
        user.setAge(89);
        user.setUserId("100");
        user.setName("Posho");
        userRepository.save(user);




    }
}
