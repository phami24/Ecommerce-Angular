package phamiz.ecommerce.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import phamiz.ecommerce.backend.dto.ApiResponse;
import phamiz.ecommerce.backend.dto.Cart.AddItemRequest;
import phamiz.ecommerce.backend.dto.Cart.CartDTO;
import phamiz.ecommerce.backend.exception.CartItemException;
import phamiz.ecommerce.backend.exception.ProductException;
import phamiz.ecommerce.backend.exception.UserException;
import phamiz.ecommerce.backend.model.Cart;
import phamiz.ecommerce.backend.model.User;
import phamiz.ecommerce.backend.service.ICartService;
import phamiz.ecommerce.backend.service.IUserService;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final ICartService cartService;
    private final IUserService userService;

    @GetMapping()
    public ResponseEntity<CartDTO> findUserByCart(@RequestHeader("Authorization") String jwt) throws UserException, CartItemException {
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart = cartService.findUserCart(user.getId());
        if (cart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
        CartDTO cartDTO = cartService.toDTO(cart);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req,
                                                     @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserProfileByJwt(jwt);
        cartService.addCartItem(user.getId(), req);
        System.out.println(req.getProductId());
        ApiResponse res = new ApiResponse();
        res.setMessage("Item added to cart");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
