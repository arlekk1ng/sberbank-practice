package ru.arlekk1ng.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.arlekk1ng.entity.Basket;
import ru.arlekk1ng.entity.Product;
import ru.arlekk1ng.repository.ProductRepository;

@Service
public class BasketService {
    private Basket basket = new Basket(new ArrayList<>(), "ZUEV");
    private ProductRepository productRepository;

    public BasketService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return basket.getProducts();
    }

    public boolean addProduct(Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());
        if (productOptional.isPresent()) {
            Product repProduct = productOptional.get();
            int repProductCount = repProduct.getCount();
            int productCount = product.getCount();

            if (repProductCount >= productCount) {
                repProduct.setCount(repProductCount - productCount);
                return basket.getProducts().add(product);
            } else if (repProduct.getCount() > 0) {
                repProduct.setCount(0);
                product.setCount(repProductCount);
                return basket.getProducts().add(product);
            }
        }
        return false;
    }

    public boolean changeProductCount(Product product) {
        for (Product basketProduct: basket.getProducts()) {
            if (basketProduct.getId() == product.getId()) {
                // ...

                basketProduct.setCount(product.getCount());
                return true;
            }
        }
        return false;
    }

    public boolean deleteProductById(long id) {
        Optional<Product> productOptional
                = basket.getProducts().stream().filter(product -> product.getId() == id).findAny();
        if (productOptional.isPresent()) {
            Product basketProduct = productOptional.get();
            Product repProduct = productRepository.findById(id).get();
            repProduct.setCount(repProduct.getCount() + basketProduct.getCount());
            return basket.getProducts().removeIf(product -> product.getId() == id);
        }
        return false;
    }
}
