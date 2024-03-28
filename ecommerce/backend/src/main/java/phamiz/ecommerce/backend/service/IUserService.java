package phamiz.ecommerce.backend.service;

import phamiz.ecommerce.backend.exception.UserException;
import phamiz.ecommerce.backend.model.User;

/**
 * Interface defining user-related operations.
 */
public interface IUserService {

    /**
     * Find a user by their ID.
     *
     * @param userId The ID of the user to find.
     * @return The found User object.
     * @throws UserException if the user with the provided ID is not found.
     */
    public User findUserById(Long userId) throws UserException;

    /**
     * Find a user's profile by their JWT token.
     *
     * @param jwt The JWT token of the user.
     * @return The User object corresponding to the JWT.
     * @throws UserException if the user corresponding to the JWT is not found.
     */
    public User findUserProfileByJwt(String jwt) throws UserException;
}
