package ru.arlekk1ng.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import ru.arlekk1ng.entity.Product;

@Repository
public class LocalProductRepository implements ProductRepository {
    private List<Product> products = new ArrayList<>(List.of(
            new Product("Яблоко", BigDecimal.valueOf(50), 10),
            new Product("Арбуз", BigDecimal.valueOf(150), 5),
            new Product("Персик", BigDecimal.valueOf(30), 7)
    ));

    @Override
    public long save(Product product) {
        products.add(product);
        return product.getId();
    }

    @Override
    public boolean update(Product product) {
        for (Product repProduct : products) {
            if (repProduct.getId() == product.getId()) {
                repProduct.setName(product.getName());
                repProduct.setPrice(product.getPrice());
                repProduct.setCount(product.getCount());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(long id) {
        return products.removeIf(product -> product.getId() == id);
    }

    @Override
    public Optional<Product> findById(long id) {
        return products.stream().filter(product -> product.getId() == id).findAny();
    }

    @Override
    public List<Product> findAll(String name) {
        if (name == null) {
            return products;
        }

        return products.stream().filter(product -> name.equals(product.getName())).toList();
    }
}
