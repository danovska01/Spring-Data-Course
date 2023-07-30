package com.danovska01.model_mapper_test.dto;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class UserDTO {

    @Expose
    private String id;
    @Expose
    private String fullName;
    @Expose
    private String message;
}
