package com.example.order_jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "order_date", length = 10)
  private String orderDate;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "order_status", length = 10)
  private OrderStatus orderStatus;
  private double totalPrice;
  private int totalQuantity;

}
