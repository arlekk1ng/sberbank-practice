package ru.arlekk1ng.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class ProductCart {
    private static long nextId = 1L;

    private final long id;
    private String promoCode;
    private List<ProductCount> productCountList;

    public ProductCart() {
        this.id = getNextId();
        this.productCountList = new ArrayList<>();
    }

    public long addProduct(Product product) {
        Optional<ProductCount> optionalProductCount = findProductCountByProductId(product.getId());
        if (optionalProductCount.isPresent()) {
            ProductCount productCount = optionalProductCount.get();
            productCount.setCount(productCount.getCount() + 1);
        } else {
            productCountList.add(new ProductCount(product, 1));
        }

        return product.getId();
    }

    public boolean updateProductCount(Product product, int newCount) {
        Optional<ProductCount> optionalProductCount = findProductCountByProductId(product.getId());
        if (optionalProductCount.isPresent()) {
            ProductCount productCount = optionalProductCount.get();
            productCount.setCount(newCount);
            return true;
        }
        return false;
    }

    public boolean deleteByProductId(long productId) {
        return productCountList.removeIf(
                productCount -> productCount.getProduct().getId() == productId);
    }

    private Optional<ProductCount> findProductCountByProductId(long productId) {
        for (ProductCount productCount: productCountList) {
            if (productCount.getProduct().getId() == productId) {
                return Optional.of(productCount);
            }
        }
        return Optional.empty();
    }

    private static long getNextId() {
        return nextId++;
    }
}
