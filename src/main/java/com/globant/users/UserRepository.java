package com.globant.users;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> users;
    private User activeUser;

    public UserRepository() {
        users = new HashMap<>();
    }

    public Map<String, User> getUsers() {
        return new HashMap<>(users);
    }

    public boolean registerUser(String firstName, String lastName, String email, String password) {
        if (users.containsKey(email)) {
            System.out.println("Email already exists!");
            return false;
        }
        if (!validatePassword(password)) {
            return false;
        }
        User user = new RegularUser(firstName, lastName, email, password, false);
        users.put(email, user);
        System.out.println("User registered successfully!");
        return true;
    }

    public boolean loginUser(String email, String password) {
        if (!users.containsKey(email)) {
            System.out.println("Email not found!");
            return false;
        }
        if (!users.get(email).getPassword().equals(password)) {
            System.out.println("Password incorrect, try again!");
            return false;
        }
        System.out.println("Login was successful!");
        activeUser = users.get(email);
        return true;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public boolean validatePassword(String password) {
        String MAX_LENGTH = "8";
        String ONE_DIGIT = "(?=.*[0-9])";
        String LOWER_CASE = "(?=.*[a-z])";
        String UPPER_CASE = "(?=.*[A-Z])";
        String regExp = "^"+ONE_DIGIT+LOWER_CASE+UPPER_CASE+".{8,20}$";
        if (password.length() < 8 || !password.matches(regExp)) {
            System.out.println("Passwords must be at least 8 characters long and should include " +
                    "at least one uppercase letter, one lowercase letter, one digit");
            return false;
        }
        return true;
    }

}
