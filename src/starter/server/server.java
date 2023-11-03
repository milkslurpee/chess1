package server;

import spark.Spark;
import handlers.*;
import dataAccess.*;
import services.*;
import com.google.gson.Gson;
import spark.Spark;

public class server {
    public static void main(String[] args) {
        // Instantiate the required DAOs
        AuthDAO authDAO = new AuthDAO();
        GameDAO gameDAO = new GameDAO();
        UserDAO userDAO = new UserDAO();

        // Instantiate the services
        ClearService clearService = new ClearService(authDAO, gameDAO, userDAO);
        CreateGameService createGameService = new CreateGameService();
        JoinGameService joinGameService = new JoinGameService(/* pass required DAOs */);
        ListGameService listGameService = new ListGameService(/* pass required DAOs */);
        LoginService loginService = new LoginService(/* pass required DAOs */);
        LogoutService logoutService = new LogoutService(/* pass required DAOs */);
        RegisterService registerService = new RegisterService(/* pass required DAOs */);

        // Instantiate Gson for JSON serialization
        Gson gson = new Gson();

        // Handlers instantiation
        ClearDbHandler clearDatabaseHandler = new ClearDbHandler(clearService, gson);
        CreateGameHandler createGameHandler = new CreateGameHandler(createGameService, gson);
        JoinGameHandler joinGameHandler = new JoinGameHandler(JoinGameService, gson);
        ListGamesHandler listGameHandler = new ListGamesHandler(ListGameService, gson);
        LoginHandler loginHandler = new LoginHandler(LoginService, gson);
        LogoutHandler logoutHandler = new LogoutHandler(LogoutService, gson);
        RegisterHandler registerHandler = new RegisterHandler(RegisterService, gson);

        // Set up HTTP routes for different APIs
        Spark.delete("/db", clearDatabaseHandler::handleClear);
        Spark.post("/game", createGameHandler::handleCreate);
        Spark.put("/game", joinGameHandler::handleJoin);
        Spark.get("/game", listGameHandler::handleList);
        Spark.post("/session", loginHandler::handleLogin);
        Spark.delete("/session", logoutHandler::handleLogout);
        Spark.post("/user", registerHandler::handleRegister);

        // Handle exceptions or errors
        Spark.exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(gson.toJson(new ErrorResponse("Internal Server Error")));
        });
    }
}