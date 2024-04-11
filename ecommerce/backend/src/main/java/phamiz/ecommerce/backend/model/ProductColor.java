package phamiz.ecommerce.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProductColor {
    private String color_name;
}
