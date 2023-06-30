package ru.arlekk1ng.service;

import org.springframework.beans.factory.annotation.Autowired;
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

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            long clientCartId = clientRepository.findById(clientId).get().getCartId();
            List<CartProduct> cartProductList = cartProductRepository.findAllByCartId(clientCartId);
            
            BigDecimal resultSum = BigDecimal.ZERO;
            Product product;
            BigDecimal multiply;

            for (CartProduct cartProduct: cartProductList) {
                product = productRepository.findById(cartProduct.getProductId()).get();
                int cartProductCount = cartProduct.getProductCount();
                multiply = product.getPrice().multiply(BigDecimal.valueOf(cartProductCount));
                product.setCount(product.getCount() - cartProductCount);

                resultSum = resultSum.add(multiply);
                productRepository.update(product);
            }

            BigDecimal newBalance = account.getBalance().subtract(resultSum);
            accountRepository.changeBalance(clientId, newBalance);

            return true;
        }

        return false;
    }
}
