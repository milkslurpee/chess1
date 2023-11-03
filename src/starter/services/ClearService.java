package services;
import chess.Game;
import dataAccess.*;
import models.User;
import responses.clearResponse;

/**
 * The ClearService class provides a service for clearing data or operations.
 */
public class ClearService {
    private final AuthDAO authDAO;
    private final GameDAO gameDAO;
    private final UserDAO userDAO;

    public ClearService(AuthDAO authDAO, GameDAO gameDAO, UserDAO userDAO){
        this.authDAO = authDAO;
        this.gameDAO = gameDAO;
        this.userDAO = userDAO;
    }

    /**
     * Clears data or performs a specific operation.
     *
     * @return A clearResponse indicating the success of the clearing operation.
     */
    public clearResponse clear() {
        try {
            authDAO.clear();
            gameDAO.clear();
            userDAO.clear();
            return new clearResponse(true, "Databases cleared successfully");
        }
        catch (DataAccessException e) {
            return new clearResponse(false, "Failed to clear the databases");
        }
    }
}