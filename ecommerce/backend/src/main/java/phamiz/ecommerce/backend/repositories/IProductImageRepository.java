package phamiz.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phamiz.ecommerce.backend.model.ProductImage;
@Repository
public interface IProductImageRepository extends JpaRepository<ProductImage,Long> {
}
