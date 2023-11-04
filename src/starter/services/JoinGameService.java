package services;
import chess.ChessGame;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import models.GameModel;
import requests.JoinGameRequest;
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

        try {
            int gameID = request.getGameID();
            GameDAO gameDAO = new GameDAO();
            GameModel game = null;
            game = gameDAO.read(gameID);
            String username = request.getUserName();
            ChessGame.TeamColor playerColor = request.getColor();

            if (game == null) {
                return new joinGameResponse(false, "Error: Game does not exist");
            }

            if (game.getWhiteUsername() == null && playerColor == ChessGame.TeamColor.WHITE) {
                game.setWhiteUsername(username);
                gameDAO.insert(game);
            }
            else if (game.getBlackUsername() == null && playerColor == ChessGame.TeamColor.BLACK) {
                game.setBlackUsername(username);
                gameDAO.insert(game);
            }
            else if (playerColor == null) {
                if (game.getWhiteUsername() == null) {
                    game.setWhiteUsername(username); // The first observer takes the white side
                } else if (game.getBlackUsername() == null) {
                    game.setBlackUsername(username); // The second observer takes the black side
                }
                return new joinGameResponse(true, "User joined as observer");
            }
            else if(game.getWhiteUsername() != null && game.getBlackUsername() != null){
                return new joinGameResponse(false, "Error: Game is full");
            }

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return new joinGameResponse(true, "Player joined successfully");
    }
}