package services;
import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import models.User;
import requests.RegisterRequest;
import responses.registerResponse;

/**
 * The RegisterService class provides a service for user registration.
 */
public class RegisterService {
    /**
     * Registers a new user based on the provided registration request.
     *
     * @param request The registration request containing the user's information.
     * @return A registerResponse indicating the success of the registration operation.
     */
    public registerResponse register(String username, String password, String email) {
        UserDAO userDAO = new UserDAO();
        try {
            User existingUser = userDAO.read(username);
            if (existingUser != null) {
                return new registerResponse(false, "Error: Username already taken");
            } else {
                User newUser = new User(username, password, email);
                userDAO.insert(newUser);
                return new registerResponse(true, "Registration successful");
            }
        } catch (DataAccessException e) {
            return new registerResponse(false, "Error: Registration failed");
        }
    }
}