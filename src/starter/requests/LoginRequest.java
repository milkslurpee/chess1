package requests;

/**
 * The LoginRequest class represents a request to log in with a specified username and password.
 */
public class LoginRequest {
    /**
     * The username provided for login.
     */
    private String username;

    /**
     * The password provided for login.
     */
    private String password;

    /**
     * Constructs a new LoginRequest with the provided username and password.
     *
     * @param username The username provided for login.
     * @param password The password provided for login.
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}