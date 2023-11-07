package server;

import spark.Spark;
import handlers.*;
import dataAccess.*;
import services.*;
import com.google.gson.Gson;
import spark.Spark;

public class server {

    public static void main(String[] args) {
        new server().run();
    }
    public void run() {
        Spark.port(8080);
        Spark.externalStaticFileLocation("web");
        // Instantiate the required DAOs
        AuthDAO authDAO = new AuthDAO();
        GameDAO gameDAO = new GameDAO();
        UserDAO userDAO = new UserDAO();

        // Instantiate the services
        ClearService clearService = new ClearService();
        CreateGameService createGameService = new CreateGameService();
        JoinGameService joinGameService = new JoinGameService();
        ListGameService listGameService = new ListGameService();
        LoginService loginService = new LoginService(/* pass required DAOs */);
        LogoutService logoutService = new LogoutService(/* pass required DAOs */);
        RegisterService registerService = new RegisterService(/* pass required DAOs */);

        // Instantiate Gson for JSON serialization
        Gson gson = new Gson();

        // Handlers instantiation
        ClearDbHandler clearDatabaseHandler = new ClearDbHandler(clearService, gson);
        CreateGameHandler createGameHandler = new CreateGameHandler(createGameService, gson);
        JoinGameHandler joinGameHandler = new JoinGameHandler(joinGameService, gson);
        ListGamesHandler listGameHandler = new ListGamesHandler(listGameService, gson);
        LoginHandler loginHandler = new LoginHandler(loginService, gson);
        LogoutHandler logoutHandler = new LogoutHandler(logoutService, gson);
        RegisterHandler registerHandler = new RegisterHandler(registerService, gson);

        // Set up HTTP routes for different APIs
        Spark.delete("/db", clearDatabaseHandler::handleClear);
        Spark.post("/game", createGameHandler::handleCreate);
        Spark.put("/game", joinGameHandler::handleJoin);
        Spark.get("/game", listGameHandler::handleList);
        Spark.post("/session", loginHandler::handleLogin);
        Spark.delete("/session", logoutHandler::handleLogout);
        Spark.post("/user", registerHandler::handleRegister);

    }
}