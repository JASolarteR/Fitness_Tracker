package com.globant.users;

import com.globant.users.exceptions.AuthFailedException;
import com.globant.users.exceptions.UserAlreadyExistsException;
import com.globant.users.exceptions.UserNotFoundException;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class UserRepository {
    private Map<String, User> users;
    private User activeUser;

    private static final String DEFAULT_ADMIN_EMAIL = "juanmanuel.romero@globant.com";
    private static final String DEFAULT_ADMIN_PASSWORD = "JuanRomero1234";

    public UserRepository() {
        users = new HashMap<>();
        initializeDefaultAdminUser();
    }

    private void initializeDefaultAdminUser() {
        AdminUser adminUser = new AdminUser("Juan", "Romero",
                DEFAULT_ADMIN_EMAIL, DEFAULT_ADMIN_PASSWORD);
        users.put(adminUser.getEmail(), adminUser);
    }

    public void registerUser(String firstName, String lastName, String email, String password) {
        if (users.containsKey(email)) {
            throw new UserAlreadyExistsException(email);
        }
        validateEmail(email);
        validatePassword(password);

        User user = new RegularUser(firstName, lastName, email, password);
        users.put(email, user);
        System.out.println("User registered successfully!");
    }

    public void loginUser(String email, String password) {
        if (!users.containsKey(email)) {
            throw new UserNotFoundException(email);
        }

        if (!users.get(email).getPassword().equals(password)) {
            throw new AuthFailedException("Incorrect credentials");
        }

        System.out.println("Login was successful!");
        activeUser = users.get(email);
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void logout() {
        activeUser = null;
    }

    private boolean validateEmail(String email) {
        String pattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!email.matches(pattern)) {
            throw new IllegalArgumentException("Enter a valid email address");
        }
        return true;
    }

    private boolean validatePassword(String password) {
        String ONE_DIGIT = "(?=.*[0-9])";
        String LOWER_CASE = "(?=.*[a-z])";
        String UPPER_CASE = "(?=.*[A-Z])";
        String regExp = "^" + ONE_DIGIT + LOWER_CASE + UPPER_CASE + ".{8,20}$";
        if (password.length() < 8 || !password.matches(regExp)) {
            throw new IllegalArgumentException("Passwords must be at least 8 characters long and include " +
                    "at least one uppercase letter, one lowercase letter, and one digit.");
        }
        return true;
    }

}
