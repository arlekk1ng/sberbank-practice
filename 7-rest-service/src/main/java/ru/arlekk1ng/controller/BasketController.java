package ru.arlekk1ng.controller;

import org.springframework.web.bind.annotation.*;
import ru.arlekk1ng.entity.Product;
import ru.arlekk1ng.service.BasketService;

import java.util.List;

@RestController
@RequestMapping("basket")
public class BasketController {
    private BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String name) {
        return basketService.getProducts();
    }

    @PostMapping
    public boolean addProduct(@RequestBody Product product) {
        return basketService.addProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteProductById(@PathVariable long id) {
        return basketService.deleteProductById(id);
    }
}
