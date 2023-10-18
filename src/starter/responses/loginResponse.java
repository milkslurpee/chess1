package responses;

public class loginResponse extends baseResponse{

    public loginResponse(String userName, String authToken, boolean success, String message) {
        super(success, message);
        this.userName = userName;
        this.authToken = authToken;

    }

    private String userName;
    private String authToken;

    public String getUserName() {
        return userName;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
