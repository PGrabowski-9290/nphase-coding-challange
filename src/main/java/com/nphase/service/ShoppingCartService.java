package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ShoppingCartService {

    private BigDecimal calculatePrice(Product product){
        if(product.getQuantity() > 3){
            return product.calculateTotalPrice().multiply(BigDecimal.valueOf(0.9));
        } else {
            return product.calculateTotalPrice();
        }
    }

    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        return shoppingCart.getProducts()
                .stream()
                .map(product -> this.calculatePrice(product).setScale(1, RoundingMode.HALF_UP))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
