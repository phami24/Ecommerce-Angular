package phamiz.ecommerce.backend.dto.Cart;

import lombok.Data;

import java.util.List;

@Data
public class CartItemDTO {
    private Long id;
    private Long cartId;
    private Long productId;
    private String productName;
    private List<String> productImageUrl;
    private int quantity;
    private Integer price;
}
