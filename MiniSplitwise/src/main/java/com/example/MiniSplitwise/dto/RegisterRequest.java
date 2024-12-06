package com.example.MiniSplitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String contact;

    public String getName(){
        return name;
    }
    public String getPersonalEmail(){
        return email;
    }
    public String getAccPassword(){
        return password;
    }
    public String getContact(){
        return contact;
    }
}