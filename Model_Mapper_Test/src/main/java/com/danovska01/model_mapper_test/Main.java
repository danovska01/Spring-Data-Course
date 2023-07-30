package com.danovska01.model_mapper_test;

import com.danovska01.model_mapper_test.dto.UserDTO;
import com.danovska01.model_mapper_test.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;


public class Main {


    public static void main(String[] args) {

        ModelMapper modelMapper = new ModelMapper();

        // create user
        User user = new User();
        user.setId("58KKN - 12");
        user.setFirstName("Smith");
        user.setLastName("Johnson");
        user.setPassword("pass");
        user.setEmail("mail");

        //print the user
        System.out.println(user);

        //turn the user into userDTO and print it
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setFullName(user.getFirstName() + " " + user.getLastName());
        System.out.println(userDTO);

        //======================================== turn to JSON ==============================================

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        System.out.println(gson.toJson(user));
        System.out.println(gson.toJson(userDTO));

        //======================================== TURN BACK to user/ userDTO from JSON =========================

        //JSON String for userDTO
        String notFormattedForDTO = """
                {
                "id": "58KKN - 12",
                 "fullName": "Smith Johnson"
                }
                """;

        // create userDTO from the JSON String
        UserDTO userDTO1 =
                gson.fromJson(notFormattedForDTO, UserDTO.class);
        System.out.println(userDTO1);
        // -----------------------------------

        //JSON String for user
        String notFormattedForUser = """
                {
                 "id": "58KKN - 12",
                 "lastName": "Johnson"
                }
                """;
        // create user from the JSON String
        User user1 =
                gson.fromJson(notFormattedForUser, User.class);
        System.out.println(user1);


    }
}
