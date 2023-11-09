package com.example.order_jpa.service;

import com.example.order_jpa.dto.OrderDto;
import com.example.order_jpa.entity.*;
import com.example.order_jpa.repository.JPAOrderRepository;
import com.example.order_jpa.repository.JPAProductRepository;
import com.example.order_jpa.repository.JPAUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final JPAOrderRepository orderRepository;
    private final JPAProductRepository productRepository;
    private final JPAUserRepository userRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getAllOrdersByUserId(Long userId) {// 리팩토링
        return orderRepository.findOrdersByUserId(userId);
    }

    public void addOrder(OrderDto orderDto) {// userid, [productId, orderQuantity]
        User user = userRepository.findById(orderDto.getUserId());
        Product product = productRepository.findById(orderDto.getProductId());

        // order 생성
        Order order = new Order();
        order.setUser(user);

        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.CREATED);
        order.setTotalPrice(product.getPrice() * orderDto.getOrderQuantity()); // 리팩토링
        order.setTotalQuantity(orderDto.getOrderQuantity());

        // orderProduct 생성
        List<OrderProduct> orderProducts = order.getOrderProducts();

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setOrderPrice(product.getPrice() * orderDto.getOrderQuantity());
        orderProduct.setOrderQuantity(orderDto.getOrderQuantity());

        orderProducts.add(orderProduct);
        order.setOrderProducts(orderProducts);
        // 영속 상태
        orderRepository.save(order);
        // product 재고 감소
        product.setQuantity(product.getQuantity() - orderDto.getOrderQuantity());
        productRepository.save(product); // 리팩토링
    }

    public void cancelOrder(Order order) {
        orderRepository.delete(order); // 리팩토링
    }

    public Order getOrderInfo(Long orderId) { // 리팩토링
        return orderRepository.findById(orderId).get();
    }

    public void updateOrder(Order order) {
        orderRepository.save(order); // 리팩토링
    }
}
