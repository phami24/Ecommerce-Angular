package phamiz.ecommerce.backend.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import phamiz.ecommerce.backend.model.ProductColor;
import phamiz.ecommerce.backend.model.ProductImage;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    private String title;
    private String description;
    private int price;
    private int quantity;
    private String brand;
    private Set<ProductColor> colors;
    private List<ProductImage> images;
    private String firstLevelCategory;
    private String secondLevelCategory;
}