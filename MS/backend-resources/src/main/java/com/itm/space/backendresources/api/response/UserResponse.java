package com.itm.space.backendresources.api.response;

import lombok.Data;

import java.util.List;
import java.util.UUID;
import java.util.UUID.*;

@Data
public class UserResponse {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final List<String> roles;
    private final List<String> groups;


    public UserResponse(String firstName, String lastName, String email, List<String> roles, List<String> groups) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.groups = groups;
    }


//    public UserResponse(UUID userId, String firstName, String email) {
//
//        this.firstName = firstName;
//        this.email = email;
//    }



}
