package dataAccess;

import models.Authtoken;

/**
 * The AuthDAO class provides data access methods for handling authentication tokens.
 */
public class AuthDAO {

    /**
     * Reads an authentication token based on its ID.
     *
     * @param authtokenID The ID of the authentication token to be retrieved.
     * @return The retrieved authentication token, or null if not found.
     * @throws DataAccessException If there is an issue accessing the data.
     */
    public Authtoken read(String authtokenID) throws DataAccessException {
        return null;
    }

    /**
     * Inserts an authentication token into the data store.
     *
     * @param authtoken The authentication token to be inserted.
     * @throws DataAccessException If there is an issue accessing the data.
     */
    public void insert(Authtoken authtoken) throws DataAccessException {
    }

    /**
     * Deletes an authentication token based on its ID.
     *
     * @param authtokenID The ID of the authentication token to be deleted.
     * @throws DataAccessException If there is an issue accessing the data.
     */
    public void delete(String authtokenID) throws DataAccessException {
    }

    /**
     * Clears all authentication tokens from the data store.
     *
     * @throws DataAccessException If there is an issue accessing the data.
     */
    public void clear() throws DataAccessException {
    }
}
