package phamiz.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import phamiz.ecommerce.backend.model.Cart;
import phamiz.ecommerce.backend.model.CartItem;
import phamiz.ecommerce.backend.model.Product;

import java.util.Set;

@Repository
public interface ICartItemRepository extends JpaRepository<CartItem,Long> {
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart = :cart "
            + "AND ci.product = :product ")
    public CartItem isCartItemExist(@Param("cart") Cart cart,
                                    @Param("product") Product product);
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId")
    public Set<CartItem> findByCartId(Long cartId);
}
