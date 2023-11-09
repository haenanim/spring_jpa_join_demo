package com.example.order_jpa.controller;

import com.example.order_jpa.dto.OrderDto;
import com.example.order_jpa.entity.Order;
import com.example.order_jpa.entity.OrderProduct;
import com.example.order_jpa.service.OrderService;
import com.example.order_jpa.service.ProductService;
import com.example.order_jpa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/list")
    public String getAllOrders(Model model) {
        List<Order> allOrders = orderService.getAllOrders();
        model.addAttribute("orders", allOrders);
        return "order/orderList";
    }

    @GetMapping("/list/{userId}")
    public String getAllOrdersByUserId(@PathVariable Long userId,
                                       Model model) {
        List<Order> allOrdersByUserId = orderService.getAllOrdersByUserId(userId);
        model.addAttribute("orders", allOrdersByUserId);
        return "order/orderList";
    }

    @GetMapping("/info/{orderId}")
    public String getOrderInfo(@PathVariable Long orderId, Model model) {
        Order order = orderService.getOrderInfo(orderId);
        model.addAttribute("order", order);
        return "order/orderInfo";
    }

    @GetMapping("/add")
    public String addOrder(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("products", productService.getAllProducts());
        return "order/orderForm";
    }


    @PostMapping("/add")
    public String addOrder(@ModelAttribute OrderDto orderDto) {
        orderService.addOrder(orderDto);
        return "redirect:/order/list";
    }
}
