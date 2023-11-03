package services;

import chess.*;
import dataAccess.*;
import models.GameModel;
import requests.CreateGameRequest;
import responses.createGameResponse;

public class CreateGameService {
    private final GameDAO gameDAO;

    public CreateGameService(GameDAO gameDAO) {
        this.gameDAO = gameDAO;
    }

    public createGameResponse createGame(CreateGameRequest request) {
        String gameName = request.getGameName();

        int newGameID = generateUniqueGameID();

        ChessGame newChessGame = new Game();

        GameModel newGame = new GameModel(newGameID, "White Player", "Black Player", gameName, newChessGame);

        try {
            gameDAO.insert(newGame); // Insert the new game into the database
            return new createGameResponse(true, "Game created successfully");
        } catch (DataAccessException e) {
            return new createGameResponse(false, "Failed to create the game");
        }
    }

    // Method to generate a unique game ID
    private int generateUniqueGameID() {
        return (int) (Math.random() * 1000);
    }
}