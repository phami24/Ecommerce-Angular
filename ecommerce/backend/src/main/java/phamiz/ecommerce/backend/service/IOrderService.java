package phamiz.ecommerce.backend.service;

import phamiz.ecommerce.backend.exception.CartItemException;
import phamiz.ecommerce.backend.exception.OrderException;
import phamiz.ecommerce.backend.model.Address;
import phamiz.ecommerce.backend.model.Order;
import phamiz.ecommerce.backend.model.User;

import java.util.List;

public interface IOrderService {
    public Order createOrder(User user, Address shippingAddress) throws CartItemException;

    public Order findOrderById(Long orderId) throws OrderException;

    public List<Order> usersOrderHistory(Long userId);

    public Order placedOrder(Long orderId) throws OrderException;

    public Order confirmedOrder(Long orderId) throws OrderException;

    public Order shippedOrder(Long orderId) throws OrderException;

    public Order deliveredOrder(Long orderId) throws OrderException;

    public Order cancelledOrder(Long orderId) throws OrderException;

    public List<Order> getAllOrders();

    public void deleteOrder(Long orderId) throws OrderException;
}