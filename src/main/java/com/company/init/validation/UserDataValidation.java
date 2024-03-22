package com.company.init.validation;

import com.fasterxml.jackson.databind.util.ArrayBuilders;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Arrays;
import java.util.List;

@Component
public class UserDataValidation {

    public Boolean checkUsername(String username) {
        // Check if username is not null
        if (username == null) {
            return false;
        }

        // Check if username length is within the range of 5 to 50
        int usernameLength = username.length();
        if (usernameLength < 5 || usernameLength > 50) {
            return false;
        }

        // Additional checks for valid username (if any)
        // You can add more validation rules here

        return true; // Username is valid
    }


    public Boolean checkEmail(String email) {
        // Regular expression for basic email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);

        // Check if email matches the pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }



    public Boolean checkVerifiedDepartment(String department) {
        // List of allowed departments
        List<String> allowedDepartments = Arrays.asList("it", "production", "devops", "developer", "hr", "sre");

        // Convert department to lowercase for case-insensitive comparison
        String lowercaseDepartment = department.toLowerCase();

        // Check if the department is in the list of allowed departments
        return allowedDepartments.contains(lowercaseDepartment);
    }



}
