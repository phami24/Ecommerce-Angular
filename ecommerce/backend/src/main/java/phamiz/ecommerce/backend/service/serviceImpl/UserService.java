package phamiz.ecommerce.backend.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import phamiz.ecommerce.backend.config.JwtProvider;
import phamiz.ecommerce.backend.exception.UserException;
import phamiz.ecommerce.backend.model.User;
import phamiz.ecommerce.backend.repositories.UserRepository;
import phamiz.ecommerce.backend.service.IUserService;

import java.util.Optional;

/**
 * Implementation of the IUserService interface for user-related operations.
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * Find a user by their ID.
     *
     * @param userId The ID of the user to find.
     * @return The found User object.
     * @throws UserException if the user with the provided ID is not found.
     */
    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            logger.info(String.format("User found with email: %s", user.get().getEmail()));
            return user.get();
        }
        logger.error(String.format("User not found with id : %s", userId));
        throw new UserException("User not found id: " + userId);
    }

    /**
     * Find a user's profile by their JWT token.
     *
     * @param jwt The JWT token of the user.
     * @return The User object corresponding to the JWT.
     * @throws UserException if the user corresponding to the JWT is not found.
     */
    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);

        User user = userRepository.findByEmail(email);

        if (user == null) {
            logger.error(String.format("User not found with email : %s", email));
            throw new UserException("User not found with email" + email);
        }
        logger.info(String.format("User found with email : %s", email));
        return user;
    }
}
