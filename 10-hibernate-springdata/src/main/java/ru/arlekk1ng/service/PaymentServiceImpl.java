package ru.arlekk1ng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.arlekk1ng.entity.BankCard;
import ru.arlekk1ng.entity.Cart;
import ru.arlekk1ng.entity.CartProduct;
import ru.arlekk1ng.entity.Product;
import ru.arlekk1ng.repository.BankCardRepository;
import ru.arlekk1ng.repository.CartProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final BankCardRepository bankCardRepository;
    private final ProductService productService;
    private final CartProductRepository cartProductRepository;

    @Autowired
    public PaymentServiceImpl(
            BankCardRepository bankCardRepository,
            ProductService productService,
            CartProductRepository cartProductRepository
    ) {
        this.bankCardRepository = bankCardRepository;
        this.productService = productService;
        this.cartProductRepository = cartProductRepository;
    }

    @Transactional
    @Override
    public boolean pay(Cart cart, String cardNumber) {
        Optional<BankCard> bankCardOptional = bankCardRepository.findBankCardByCardNumber(cardNumber);
        if (bankCardOptional.isEmpty()) {
            return false;
        }

        BankCard bankCard = bankCardOptional.get();
        Product product;
        int countInStock, neededCount;
        BigDecimal multiply, subtract;

        List<CartProduct> cartProductList = cartProductRepository.findCartProductsByCart(cart);
        for (CartProduct cartProduct: cartProductList) {
            product = cartProduct.getProduct();
            countInStock = product.getCount();
            neededCount = cartProduct.getProductCount();

            if (countInStock < neededCount) {
                throw new RuntimeException("товара " + product + " не хватает на складе");
            }

            product.setCount(countInStock - neededCount);

            multiply = product.getPrice().multiply(BigDecimal.valueOf(neededCount));
            subtract = bankCard.getBalance().subtract(multiply);

            if (subtract.signum() == -1) {
                throw new RuntimeException("не достаточно баланса на банковской карте");
            }

            bankCard.setBalance(subtract);

            cartProductRepository.deleteById(cartProduct.getId());
            productService.update(product);
        }

        bankCardRepository.save(bankCard);

        return true;
    }
}
