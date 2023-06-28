package ru.arlekk1ng.repository;

import org.springframework.stereotype.Repository;
import ru.arlekk1ng.entity.Product;
import ru.arlekk1ng.entity.ProductCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LocalProductCartRepository implements ProductCartRepository {
    private List<ProductCart> productCarts = new ArrayList<>();
    private ProductRepository productRepository;

    public LocalProductCartRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public long save(ProductCart productCart) {
        /*
         * При добавлении корзины в репозиторий продуктовых корзин, так же добавляются записи продуктов,
         * которых не было в репозитории продуктов, в репозиторий продуктов
         */
        List<Product> products = productCart.getProductCountList().stream()
                .map(productCount -> productCount.getProduct()).toList();
        for (Product product: products) {
            Optional<Product> optionalProduct = productRepository.findById(product.getId());
            if (optionalProduct.isEmpty()) {
                long createdProductId = productRepository.save(product.getName(), product.getPrice());
                productRepository.findById(createdProductId).get().setId(product.getId());
            }
        }

        productCarts.add(productCart);
        return productCart.getId();
    }

    @Override
    public Optional<ProductCart> findById(long productCartId) {
        return productCarts.stream()
                .filter(cart -> cart.getId() == productCartId)
                .findAny();
    }

    @Override
    public List<ProductCart> findAll() {
        return new ArrayList<>(productCarts);
    }
}
