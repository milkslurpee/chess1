package handlers;

import com.google.gson.Gson;
import requests.JoinGameRequest;
import responses.joinGameResponse;
import services.JoinGameService;
import spark.Request;
import spark.Response;
public class JoinGameHandler {
    private final JoinGameService joinGameService;
    private final Gson gson;

    public JoinGameHandler(JoinGameService joinGameService, Gson gson) {
        this.joinGameService = joinGameService;
        this.gson = gson;
    }

    public Object handleJoin(Request request, Response response) {
        response.type("application/json");

        String userName = request.headers("authorization");

        JoinGameRequest joinGameRequest = gson.fromJson(request.body(), JoinGameRequest.class);

        joinGameResponse joinGameResponse = joinGameService.joinGame(joinGameRequest);

        if (joinGameResponse.isSuccess()) {
            response.status(200);
            return gson.toJson(new joinGameResponse(true, "Game joined Successfully"));
        } else {
            response.status(500);
            return gson.toJson(new joinGameResponse(false, "Failed to join game"));
        }
    }
}