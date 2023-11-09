package com.example.order_jpa.controller;

import com.example.order_jpa.entity.Product;
import com.example.order_jpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }
  @GetMapping
  public String index() {
    return "redirect:/product/list";
  }
  @GetMapping("/list")
  public String list(Model model) {
    model.addAttribute("products", productService.findAll());
    return "product/productList";
  }
  @PostMapping("/add")
  public String add(Product product) {
    productService.save(product);
    return "redirect:/product/list";
  }
}
