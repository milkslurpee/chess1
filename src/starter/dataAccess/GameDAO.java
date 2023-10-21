package dataAccess;

import models.GameModel;

import java.util.Map;

/**
 * The GameDAO class provides data access methods for managing game data.
 */
public class GameDAO {

    private Map<Integer, GameModel> gameMap;

    /**
     * Reads a game based on its ID.
     *
     * @param gameID The ID of the game to be retrieved.
     * @return The retrieved GameModel, or null if the game is not found.
     * @throws DataAccessException If there is an issue accessing the data.
     */
    public GameModel read(int gameID) throws DataAccessException {
        if(!gameMap.containsKey(gameID)){
            throw new DataAccessException("Game doesn't exist");
        }
        else{
            return gameMap.get(gameID);
        }
    }

    /**
     * Inserts a GameModel into the data store.
     *
     * @param game The GameModel to be inserted.
     * @throws DataAccessException If there is an issue accessing the data.
     */
    public void insert(GameModel game) throws DataAccessException {
        Integer gameID = game.getGameID();
        if(gameMap.containsKey(gameID)){
            throw new DataAccessException("Game already exists");
        }
        else{
            gameMap.put(gameID, game);
        }
    }

    /**
     * Deletes a game based on its ID.
     *
     * @param gameID The ID of the game to be deleted.
     * @throws DataAccessException If there is an issue accessing the data.
     */
    public void delete(int gameID) throws DataAccessException {
        if(!gameMap.containsKey(gameID)){
            throw new DataAccessException("Game doesn't exist");
        }
        else{
            gameMap.remove(gameID);
        }
    }

    /**
     * Clears all game data from the data store.
     *
     * @throws DataAccessException If there is an issue accessing the data.
     */
    public void clear() throws DataAccessException {
        if(gameMap.isEmpty()){
            throw new DataAccessException("There are no Games to clear");
        }
        else{
            gameMap.clear();
        }
    }
}
