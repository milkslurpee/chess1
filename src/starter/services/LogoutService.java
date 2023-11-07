package services;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import responses.logoutResponse;

/**
 * The LogoutService class provides a service for user logout.
 */
public class LogoutService {
    /**
     * Logs out the user, terminating their current session.
     *
     * @return A logoutResponse indicating the success of the logout operation.
     */
    public logoutResponse logout(String authToken) {
        AuthDAO authDAO = new AuthDAO();
        try {
            authDAO.delete(authToken);
            return new logoutResponse(true, "Logout successful");
        } catch (DataAccessException e) {
            return new logoutResponse(false, "Error: Failed to logout");
        }
    }
}