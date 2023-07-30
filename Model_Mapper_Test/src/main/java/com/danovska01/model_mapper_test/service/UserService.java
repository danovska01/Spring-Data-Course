package com.danovska01.model_mapper_test.service;

import com.danovska01.model_mapper_test.convert.UserDTOConverter;
import com.danovska01.model_mapper_test.dto.UserDTO;
import com.danovska01.model_mapper_test.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserDTOConverter userDTOConverter;

    public UserDTO getUser() {
        User user = new User();
        user.setId("1234");
        user.setEmail("email@comblabla");
        user.setFirstName("Georgi");
        user.setLastName("Georgiev");
        user.setPassword("pass");

        UserDTO userDTO = userDTOConverter.convertUserToUserDTO(user);

        return userDTO;
    }
}
