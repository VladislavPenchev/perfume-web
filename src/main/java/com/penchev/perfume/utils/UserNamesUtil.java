package com.penchev.perfume.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserNamesUtil {

    public static String createUserName(String firstAndLastNames) {
        return String.format("%s", firstAndLastNames.replace("\\s+", ""));
    }

    public static String concatUserNames(String firstName, String lastName) {
        return String.format("%s %s", firstName, lastName);
    }

    public static List<String> splitUserNames(String firstAndLastNames) {
        List<String> names = Arrays.stream(firstAndLastNames.split("\\s+"))
                .map(name -> name.substring(0, 1).toUpperCase().concat(name.substring(1)))
                .collect(Collectors.toList());

        if (names.size() > 2) {
            throw new IllegalArgumentException("Field 'firstAndLastNames' can't contains more than two names.");
        } else if (names.size() == 1) {
            throw new IllegalArgumentException("Field 'firstAndLastNames' can't contains only one name.");
        }

        return names;
    }
}
