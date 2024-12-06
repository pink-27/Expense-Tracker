package com.example.MiniSplitwise.dto;
import com.example.MiniSplitwise.model.User;
import lombok.Data;

import java.util.*;

@Data
public class UserDTO {
    private String name;
    private String accPassword;
    private UUID userId;
    private String personalEmail;
    private String contact;
    public User getUserFromDto(){
        User user = new User();

        user.setName(name);
        user.setAccPassword(accPassword);
        user.setUserId(userId);
        user.setPersonalEmail(personalEmail);
        user.setContact(contact);

        return user;
    }
    public String getName() {
        return name;
    }

    public String getAccPassword() {
        return accPassword;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public String getContact() {
        return contact;
    }
}
