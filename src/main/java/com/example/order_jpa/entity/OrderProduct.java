package com.example.order_jpa.entity;

import com.example.order_jpa.exception.NoEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    private Long orderProdcutId;

    @ManyToOne(fetch = FetchType.LAZY)  // 지연로딩
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)  // 지연로딩
    @JoinColumn(name = "product_id")
    private Product product;

    private long orderPrice;
    private int orderQuantity;

    //        List<OrderProduct> orderProducts = order.getOrderProducts();
//        orderProducts.add(orderProduct);
//        orderProduct.setOrder(order);
//        order.setOrderProducts(orderProducts);

    public static OrderProduct createOrderProducts(Product product, int orderQuantity) throws NoEnoughStockException {


        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setOrderPrice(product.getPrice() * orderQuantity);
        orderProduct.setOrderQuantity(orderQuantity);

        product.decreseQuantity(orderQuantity);

        return orderProduct;
    }


    public void cancelOrderProduct() {
        this.getProduct().increaseQuantity(this.getOrderQuantity());
    }
}
