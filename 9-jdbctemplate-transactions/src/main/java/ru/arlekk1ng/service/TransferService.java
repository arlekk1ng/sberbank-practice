package ru.arlekk1ng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.arlekk1ng.entity.Account;
import ru.arlekk1ng.entity.CartProduct;
import ru.arlekk1ng.entity.Product;
import ru.arlekk1ng.repository.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransferService {
    private AccountRepository accountRepository;
    private ClientRepository clientRepository;
    private CartProductRepository cartProductRepository;
    private ProductRepository productRepository;

    @Autowired
    public TransferService(
            AccountRepository accountRepository,
            ClientRepository clientRepository,
            CartProductRepository cartProductRepository,
            ProductRepository productRepository
    ) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.cartProductRepository = cartProductRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public boolean transferMoney(long clientId) {
        Optional<Account> accountOptional = accountRepository.findByClientId(clientId);

        if (accountOptional.isEmpty()) {
            return false;
        }

        long clientCartId = clientRepository.findById(clientId).get().getCartId();
        List<CartProduct> clientCartProductList = cartProductRepository.findAllByCartId(clientCartId);

        Product product;
        Account account = accountOptional.get();

        for (CartProduct cartProduct: clientCartProductList) {
            product = productRepository.findById(cartProduct.getProductId()).get();
            int cartProductCount = cartProduct.getProductCount();

            if (!checkIfGoodsInStock(product, cartProductCount)) {
                throw new RuntimeException("товара " + product + " не хватает на складе");
            }
            product.setCount(product.getCount() - cartProductCount);

            account.setBalance(
                    account.getBalance().subtract(
                            product.getPrice().multiply(BigDecimal.valueOf(cartProductCount))
                    )
            );
            if (account.getBalance().signum() == -1) {
                throw new RuntimeException("не достаточно баланса на аккаунте");
            }

            productRepository.update(product);
            cartProductRepository.deleteById(cartProduct.getId());
        }

        accountRepository.changeBalance(clientId, account.getBalance());

        return true;
    }

    private boolean checkIfGoodsInStock(Product product, int requiredAmount) {
        return product.getCount() >= requiredAmount;
    }
}
