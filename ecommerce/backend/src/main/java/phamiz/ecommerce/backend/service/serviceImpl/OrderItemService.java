package phamiz.ecommerce.backend.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import phamiz.ecommerce.backend.model.OrderItem;
import phamiz.ecommerce.backend.repositories.IOrderItemRepository;
import phamiz.ecommerce.backend.service.IOrderItemService;

@Service
@RequiredArgsConstructor
public class OrderItemService implements IOrderItemService {

    private final IOrderItemRepository orderItemRepository;

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}