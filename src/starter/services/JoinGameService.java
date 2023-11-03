package services;
import chess.ChessGame;
import chess.Game;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import models.GameModel;
import requests.CreateGameRequest;
import requests.JoinGameRequest;
import responses.createGameResponse;
import responses.joinGameResponse;

/**
 * The JoinGameService class provides a service for joining an existing game.
 */
public class JoinGameService {
    /**
     * Joins an existing game based on the provided request.
     *
     * @param request The request containing details for joining the game.
     * @return A joinGameResponse indicating the success of the joining operation.
     */
    public joinGameResponse joinGame(JoinGameRequest request) {
        int gameID = request.getGameID();

        GameModel newGame = new GameModel(gameID, "White Player", "Black Player", gameName, newChessGame);

        try {
            GameDAO gameDAO = new GameDAO();
            gameDAO.insert(newGame);
            return new joinGameResponse(true, "Game joined successfully");
        } catch (DataAccessException e) {
            return new joinGameResponse(false, "Failed to create the game");
        }
    }

    // Method to generate a unique game ID
    private int generateUniqueGameID() {
        return (int) (Math.random() * 1000);
    }
}