package com.example.order_jpa.controller;

import com.example.order_jpa.dto.ProductUpdateDto;
import com.example.order_jpa.entity.Product;
import com.example.order_jpa.service.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    public String getAllProducts(Model model) {
        List<Product> products =  productService.getAllProducts();
        model.addAttribute("products", products);
        return "product/productList";
    }

    @GetMapping("/add")
    public String addProduct() {
        return "product/productForm";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/product/list";
    }

    @PostMapping("/list")
    public String deleteProduct(@RequestParam long productId) {
        productService.deleteProduct(productId);
        return "redirect:/product/list";
    }

    @GetMapping("/info/{productId}")
    public String getProductInfo(Model model, @PathVariable Long productId) {
        Product product = productService.getProductInfo(productId);
        model.addAttribute("product", product);
        return "product/productInfo";
    }

    @GetMapping("/update/{productId}")
    public String updateProduct(Model model, @PathVariable Long productId) {
        Product product = productService.getProductInfo(productId);
        model.addAttribute("product", product);
        return "product/productUpdate";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute ProductUpdateDto productUpdateDto) {
        productService.updateProduct(productUpdateDto);
        return "redirect:/product/list";
    }
}
