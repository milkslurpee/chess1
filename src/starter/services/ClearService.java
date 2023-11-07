package services;
import chess.Game;
import dataAccess.*;
import models.User;
import responses.clearResponse;

/**
 * The ClearService class provides a service for clearing data or operations.
 */
public class ClearService {
    /**
     * Clears data or performs a specific operation.
     *
     * @return A clearResponse indicating the success of the clearing operation.
     */
    public clearResponse clear() {
        try {
            new AuthDAO().clear();
            new GameDAO().clear();
            new UserDAO().clear();
            return new clearResponse(true, "Databases cleared successfully");
        }
        catch (DataAccessException e) {
            return new clearResponse(false, "Failed to clear the databases");
        }
    }
}