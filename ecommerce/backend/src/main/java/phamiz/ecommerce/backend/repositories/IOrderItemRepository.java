package phamiz.ecommerce.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phamiz.ecommerce.backend.model.OrderItem;
@Repository
public interface IOrderItemRepository extends JpaRepository<OrderItem,Long> {
}
