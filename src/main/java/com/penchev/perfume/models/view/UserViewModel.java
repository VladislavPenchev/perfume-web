package com.penchev.perfume.models.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserViewModel {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String city;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private List<String> authorities;
}
