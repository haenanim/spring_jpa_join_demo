package com.example.order_jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long orderId;

  @ManyToOne(fetch = FetchType.LAZY)  // 지연로딩
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderProduct> orderProducts = new ArrayList<>();

  @Column(name = "order_date", length = 10)
  private LocalDateTime orderDate;
  @Enumerated(value = EnumType.STRING)
  @Column(name = "order_status", length = 10)
  private OrderStatus orderStatus;
  private long totalPrice;
  private int totalQuantity;

  public static Order createOrder(User user, OrderProduct... orderProduct) {
    Long totalPrice = 0L;
    int totalQuantity = 0;

    Order order = new Order();
    order.setUser(user);
    order.setOrderDate(LocalDateTime.now());
    order.setOrderStatus(OrderStatus.CREATED);
    for (OrderProduct op : orderProduct) {
      totalPrice += op.getOrderPrice();
      totalQuantity += op.getOrderQuantity();
      order.addOrderProduct(op);
      //order.setOrderProducts(orderProducts);
    }
    order.setTotalPrice(totalPrice); // 리팩토링
    order.setTotalQuantity(totalQuantity);
    return order;
  }

  private void addOrderProduct(OrderProduct orderProduct) {
    orderProducts.add(orderProduct);
    orderProduct.setOrder(this);
  }

  public void cancel() {
    this.setOrderStatus(OrderStatus.CANCELED);
    for(OrderProduct orderProduct : this.orderProducts) {
      orderProduct.cancelOrderProduct();
      orderProduct.getProduct().increaseQuantity(orderProduct.getOrderQuantity());
    }
  }
}
