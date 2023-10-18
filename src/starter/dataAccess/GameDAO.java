package dataAccess;

import models.GameModel;

/**
 * The GameDAO class provides data access methods for managing game data.
 */
public class GameDAO {

    /**
     * Reads a game based on its ID.
     *
     * @param gameID The ID of the game to be retrieved.
     * @return The retrieved GameModel, or null if the game is not found.
     * @throws DataAccessException If there is an issue accessing the data.
     */
    public GameModel read(int gameID) throws DataAccessException {
        return null;
    }

    /**
     * Inserts a GameModel into the data store.
     *
     * @param game The GameModel to be inserted.
     * @throws DataAccessException If there is an issue accessing the data.
     */
    public void insert(GameModel game) throws DataAccessException {
    }

    /**
     * Deletes a game based on its ID.
     *
     * @param gameID The ID of the game to be deleted.
     * @throws DataAccessException If there is an issue accessing the data.
     */
    public void delete(int gameID) throws DataAccessException {
    }

    /**
     * Clears all game data from the data store.
     *
     * @throws DataAccessException If there is an issue accessing the data.
     */
    public void clear() throws DataAccessException {
    }
}
