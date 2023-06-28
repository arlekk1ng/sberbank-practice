package ru.arlekk1ng.repository;

import org.springframework.stereotype.Repository;
import ru.arlekk1ng.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LocalProductRepository implements ProductRepository {
    private List<Product> products = new ArrayList<>();

    @Override
    public long save(String name, BigDecimal price) {
        Product product = new Product(name, price);
        products.add(product);
        return product.getId();
    }

    @Override
    public Optional<Product> findById(long productId) {
        return products.stream()
                .filter(product -> product.getId() == productId)
                .findAny();
    }

    @Override
    public List<Product> findAll(String name) {
        if (name == null || name.isBlank()) {
            return new ArrayList<>(products);
        }
        return products.stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @Override
    public boolean update(Product product) {
        for (Product repProduct: products) {
            if (repProduct.getId() == product.getId()) {
                repProduct.setName(product.getName());
                repProduct.setPrice(product.getPrice());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(long productId) {
        return products.removeIf(product -> product.getId() == productId);
    }
}
