package phamiz.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phamiz.ecommerce.backend.model.User;

/**
 * Repository interface for accessing User entities in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by their email address.
     *
     * @param email The email address of the user.
     * @return The User object corresponding to the email.
     */
    User findByEmail(String email);
}
