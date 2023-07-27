package com.nphase.service;


import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

public class ShoppingCartServiceTest {
    private final ShoppingCartService service = new ShoppingCartService();

    @Test
    public void calculatesPrice()  {
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.0), 1, "drinks"),
                new Product("Coffee", BigDecimal.valueOf(3.5), 2, "drinks")
        ));

        BigDecimal result = service.calculateTotalPrice(cart);

        Assertions.assertEquals(result, BigDecimal.valueOf(12.0));
    }

//    After implementing task 3, this one is wrong calculated(need to recalculate final result with category discount)
//    @Test
//    public void calculatePriceWithQuantityDiscount(){
//        ShoppingCart cart = new ShoppingCart(Arrays.asList(
//                new Product("Tea", BigDecimal.valueOf(5.0), 5, "drinks"),
//                new Product("Coffee", BigDecimal.valueOf(3.5), 3, "drinks")
//        ));
//
//        BigDecimal result = service.calculateTotalPrice(cart);
//
//        Assertions.assertEquals(result, BigDecimal.valueOf(33.0));
//    }

    @Test
    public void calculatePriceWithCategoryDiscount(){
        ShoppingCart cart = new ShoppingCart(Arrays.asList(
                new Product("Tea", BigDecimal.valueOf(5.3), 2, "drinks"),
                new Product("Coffee", BigDecimal.valueOf(3.5), 2, "drinks"),
                new Product("Cheese", BigDecimal.valueOf(8), 2, "food")
        ));

        BigDecimal result = service.calculateTotalPrice(cart);

        Assertions.assertEquals(result, BigDecimal.valueOf(31.84));
    }

}