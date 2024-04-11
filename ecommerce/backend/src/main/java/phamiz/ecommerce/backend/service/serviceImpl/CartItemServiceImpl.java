package phamiz.ecommerce.backend.service.serviceImpl;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import phamiz.ecommerce.backend.exception.CartItemException;
import phamiz.ecommerce.backend.exception.UserException;
import phamiz.ecommerce.backend.model.Cart;
import phamiz.ecommerce.backend.model.CartItem;
import phamiz.ecommerce.backend.model.Product;
import phamiz.ecommerce.backend.model.User;
import phamiz.ecommerce.backend.repositories.ICartItemRepository;
import phamiz.ecommerce.backend.repositories.ICartRepository;
import phamiz.ecommerce.backend.service.ICartItemService;
import phamiz.ecommerce.backend.service.IUserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements ICartItemService {

    private final ICartItemRepository cartItemRepository;
    private final IUserService userService;
    private final ICartRepository cartRepository;
    private static final Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        CartItem createdCartItem = cartItemRepository.save(cartItem);
        logger.info("Create Cart Item success!");
        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, int quantity) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(id);
        if(cartItem == null ){
            logger.error("Cart Item is null");
        }
        cartItem.setQuantity(quantity);
        cartItem.setPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice());
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product) {
        CartItem cartItem = cartItemRepository.isCartItemExist(cart, product);
        if(cartItem == null ){
             logger.error("Cart Item is null");
        }
        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException {
        CartItem cartItem = findCartItemById(cartItemId);
        logger.info("Delete Cart Item success!");
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> op = cartItemRepository.findById(cartItemId);
        if (op.isPresent()) {
            return op.get();
        }
        logger.error("Cart item not found with id" + cartItemId);
        throw new CartItemException("Cart item not found with id" + cartItemId);
    }
}