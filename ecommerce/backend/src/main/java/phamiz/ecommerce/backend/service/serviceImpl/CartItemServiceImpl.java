package phamiz.ecommerce.backend.service.serviceImpl;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import phamiz.ecommerce.backend.dto.Cart.CartItemDTO;
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

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        if (cartItem == null) {
            logger.error("Cart Item is null");
        }
        cartItem.setQuantity(quantity);
        cartItem.setPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice());
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product) {
        CartItem cartItem = cartItemRepository.isCartItemExist(cart, product);
        if (cartItem == null) {
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

    @Override
    public Set<CartItem> findCartItemByCartId(Long cartId) throws CartItemException {
        Set<CartItem> cartItems = cartItemRepository.findByCartId(cartId);
        if (!cartItems.isEmpty()) {
            logger.info("Cart Items was found",cartItems);
            return cartItems;
        }
        logger.error("Cart item not found with id" + cartId);
        return null;
    }

    @Override
    public CartItemDTO toDTO(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setCartId(cartItem.getCart().getId());
        cartItemDTO.setProductId(cartItem.getProduct().getId());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setPrice(cartItem.getPrice());
        cartItemDTO.setProductName(cartItem.getProduct().getProduct_name());
        cartItemDTO.setProductImageUrl(cartItem.getProduct().getImages().stream()
                .map((productImage -> productImage.getImageUrl().toString())).collect(Collectors.toList()));
        return cartItemDTO;
    }
}