package phamiz.ecommerce.backend.dto.Cart;

import lombok.Data;
import phamiz.ecommerce.backend.model.CartItem;

import java.util.HashSet;
import java.util.Set;
@Data
public class CartDTO {
    private Long id;
    private Long userId;
    private Set<CartItemDTO> cartItems = new HashSet<>();
    private double totalPrice;
    private int totalItem;
}
