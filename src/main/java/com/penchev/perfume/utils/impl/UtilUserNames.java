package com.penchev.perfume.utils.impl;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class UtilUserNames {

    private String firstName;
    private String lastName;

    public void splitUserNames(String firstAndLastNames) {
        List<String> names = Arrays.stream(firstAndLastNames.split("\\s+"))
                .map(name -> name.substring(0, 1).toUpperCase().concat(name.substring(1)))
                .collect(Collectors.toList());

        if (names.size() > 2) {
            throw new IllegalArgumentException("Field 'firstAndLastNames' can't contains more than two names.");
        } else if (names.size() == 1) {
            throw new IllegalArgumentException("Field 'firstAndLastNames' can't contains only one name.");
        }

        firstName = names.get(0);
        lastName = names.get(1);
    }

    public String createUserName(String firstAndLastNames){
        return String.format("%s",firstAndLastNames.replace("\\s+",""));
    }

    public String concatUserNames(String firstName, String lastName) {
        return String.format("%s %s", firstName, lastName);
    }
}
