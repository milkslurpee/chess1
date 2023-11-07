package services;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import models.GameModel;
import responses.listResponse;

import java.util.Map;

/**
 * The ListGameService class creates a service for listing all the games.
 */
public class ListGameService {
    /**
     * Lists all the games.
     *
     * @return A listResponse indicating the success of the listing operation.
     */
    public listResponse list() {
        GameDAO gameDAO = new GameDAO();
        Map<Integer, GameModel> map;
        try {
            map = gameDAO.getGameMap();
        } catch (DataAccessException e) {
            return new listResponse(false, "No games to list", null);
        }
        return new listResponse(true, "Games listed successfully", map);
    }
}