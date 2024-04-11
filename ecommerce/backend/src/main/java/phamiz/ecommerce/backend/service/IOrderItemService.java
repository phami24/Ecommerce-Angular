package phamiz.ecommerce.backend.service;

import phamiz.ecommerce.backend.model.OrderItem;

public interface IOrderItemService {
    public OrderItem createOrderItem(OrderItem orderItem);
}