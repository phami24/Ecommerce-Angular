package phamiz.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import phamiz.ecommerce.backend.model.Cart;
@Repository
public interface ICartRepository extends JpaRepository<Cart,Long> {
    @Query("SELECT cart FROM Cart cart WHERE cart.user.id = :userId")
    public Cart findByUserId(@Param("userId") Long userId);
}
