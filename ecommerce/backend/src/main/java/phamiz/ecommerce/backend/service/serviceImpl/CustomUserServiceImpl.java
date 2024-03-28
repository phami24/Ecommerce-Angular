package phamiz.ecommerce.backend.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import phamiz.ecommerce.backend.model.User;
import phamiz.ecommerce.backend.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom implementation of UserDetailsService for loading user details by username (email).
 */
@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomUserServiceImpl.class);

    /**
     * Load user details by username (email).
     *
     * @param email The email address of the user.
     * @return UserDetails object containing user details.
     * @throws UsernameNotFoundException if the user with the provided email is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            logger.error(String.format("User not found with email : %s", email));
            throw new UsernameNotFoundException("User not found with email -" + email);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        logger.info(String.format("User found with email: %s", email));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
