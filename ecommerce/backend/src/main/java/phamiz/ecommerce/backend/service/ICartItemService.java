package phamiz.ecommerce.backend.service;

import phamiz.ecommerce.backend.dto.Cart.CartItemDTO;
import phamiz.ecommerce.backend.exception.CartItemException;
import phamiz.ecommerce.backend.exception.UserException;
import phamiz.ecommerce.backend.model.Cart;
import phamiz.ecommerce.backend.model.CartItem;
import phamiz.ecommerce.backend.model.Product;

import java.util.List;
import java.util.Set;

public interface ICartItemService {
    public CartItem createCartItem(CartItem cartItem);
    public CartItem updateCartItem(Long userId, Long id, int quantity) throws CartItemException, UserException;
    public CartItem isCartItemExist(Cart cart, Product product);
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;
    public CartItem findCartItemById(Long cartItemId) throws CartItemException;
    Set<CartItem> findCartItemByCartId(Long cartId) throws CartItemException;

    CartItemDTO toDTO(CartItem cartItem);
}

