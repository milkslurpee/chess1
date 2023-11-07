package handlers;

import com.google.gson.Gson;
import dataAccess.UserDAO;
import responses.registerResponse;
import services.RegisterService;
import spark.Request;
import spark.Response;

public class RegisterHandler {

    private final RegisterService registerService;
    private final Gson gson;

    public RegisterHandler(RegisterService registerService, Gson gson) {
        this.registerService = registerService;
        this.gson = gson;
    }

    public String handleRegister(Request request, Response response) {
        response.type("application/json");

        try {
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            String email = request.queryParams("email");

            registerResponse registerResponse = registerService.register(username, password, email);

            if (registerResponse.isSuccess()) {
                response.status(200);
                return gson.toJson(registerResponse);
            } else {
                response.status(400);
                return gson.toJson(registerResponse);
            }
        } catch (Exception e) {
            response.status(500);
            return gson.toJson(new registerResponse(false, "Error: Internal Server Error"));
        }
    }
}