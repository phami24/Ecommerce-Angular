package phamiz.ecommerce.backend.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phamiz.ecommerce.backend.model.Category;
import phamiz.ecommerce.backend.model.ProductColor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private String productName;
    private int quantity;
    private int price;
    private String brand;
    private Set<String> productColors;
    private Category category;
    private double rating;
    private List<ReviewDTO> reviews;
    private List<String> images;
    private LocalDateTime createdAt;

    // Constructors, getters, and setters
}
