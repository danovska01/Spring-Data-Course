package com.example.workshop1.controller;

import com.example.workshop1.models.dto.RegistrationDTO;
import com.example.workshop1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/register")
    public String registerView(RegistrationDTO registrationDto) {
        return "user/register";
    }

    @PostMapping(value = "/users/register")
    public String doRegister(@Valid RegistrationDTO registrationDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user/register";
        }

        this.userService.register(registrationDto);
        return "user/login";
    }


}
