package com.danovska01.model_mapper_test.convert;

import com.danovska01.model_mapper_test.dto.UserDTO;
import com.danovska01.model_mapper_test.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class UserDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertUserToUserDTO(User user) {

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setFullName(user.getFirstName() + " " + user.getLastName());
        userDTO.setMessage("Date is: " + new Date());
        return userDTO;
    }

    public User convertUserDTOtoUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }
}
