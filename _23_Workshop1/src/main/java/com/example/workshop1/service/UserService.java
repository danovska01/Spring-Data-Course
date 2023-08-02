package com.example.workshop1.service;

import com.example.workshop1.models.User;
import com.example.workshop1.models.dto.RegistrationDTO;
import com.example.workshop1.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public void register(RegistrationDTO dto) {
        User user = this.modelMapper.map(dto, User.class);

        this.userRepository.save(user);
    }
}
