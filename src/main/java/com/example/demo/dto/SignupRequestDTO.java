package com.example.demo.dto;

import lombok.Data;

@Data
public class SignupRequestDTO {
    private String fullname;
    private String username;
    private String password;
}
