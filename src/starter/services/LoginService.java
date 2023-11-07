package services;
import dataAccess.DataAccessException;
import models.Authtoken;
import requests.LoginRequest;
import responses.listResponse;
import responses.loginResponse;
import dataAccess.UserDAO;
import dataAccess.AuthDAO;
import models.User;
import java.util.UUID;

/**
 * The LoginService class provides a service for user login.
 */
public class LoginService {
    /**
     * Logs in a user based on the provided login request.
     *
     * @param loginRequest The login request containing the user's credentials.
     * @return A loginResponse indicating the success of the login operation and providing user information.
     */
    public loginResponse login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Validate the user's credentials
        UserDAO userDAO = new UserDAO();
        User user = null;
        try {
            user = userDAO.read(username);
        } catch (DataAccessException e) {
            return new loginResponse(null, null,false, "User doesn't exist");
        }

        if (user != null && user.getPassword().equals(password)) {
            AuthDAO authDAO = new AuthDAO();
            String authToken = generateAuthToken();
            try {
                authDAO.insert(new Authtoken(authToken, username));
            } catch (DataAccessException e) {
                throw new RuntimeException(e);
            }
            return new loginResponse(username, authToken,true, "Login successful");
        } else {
            return new loginResponse(null, null,false, "Invalid username or password");
        }
    }

    public String generateAuthToken(){
        return UUID.randomUUID().toString();
    }
}