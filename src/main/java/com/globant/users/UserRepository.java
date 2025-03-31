package com.globant.users;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    Map<String, User> users;

    public UserRepository() {
        users = new HashMap<>();
    }

    public Map<String, User> getUsers() {
        return new HashMap<>(users);
    }

    public boolean registerUser(User user){
        if (users.containsKey(user.getEmail())){
            System.out.println("Email already exists!");
            return false;
        }
        users.put(user.getEmail(), user);
        System.out.println("User registered successfully!");

        return true;
    }

    public boolean loginUser(String email, String password){
        if (!users.containsKey(email)){
            System.out.println("Email not found!");
            return false;
        }
        if (!users.get(email).getPassword().equals(password)){
            System.out.println("Password incorrect, try again!");
            return false;
        }
        System.out.println("Login was successful!");
        return true;
    }

}
