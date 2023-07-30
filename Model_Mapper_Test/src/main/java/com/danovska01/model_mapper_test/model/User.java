package com.danovska01.model_mapper_test.model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class User {
    // if there is no Expose, it will not go in the json
    @Expose
    private String id;
    private String firstName;
    @Expose
    private String lastName;
    private String email;
    private String password;
}
