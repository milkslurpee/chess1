package requests;

public class LoginRequest {
    private String username;
    private String password;

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
