package com.nphase.service;

import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCartService {
    public BigDecimal calculateTotalPrice(ShoppingCart shoppingCart) {
        Map<String, List<Product>> products = shoppingCart.getProducts()
                .stream()
                .collect(Collectors.groupingBy(Product::getCategory));

        return products.values()
                .stream()
                .map(this::calculateCategoryTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateCategoryTotal(List<Product> it) {
        int categoryQuantity = it.stream()
                .mapToInt(Product::getQuantity)
                .sum();

        BigDecimal categoryTotal = it.stream()
                .map(this::calculateProductPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (categoryQuantity > 3) {
            categoryTotal = categoryTotal.multiply(BigDecimal.valueOf(0.9));
        }

        return categoryTotal;
    }

    private BigDecimal calculateProductPrice(Product product) {
        if (product.getQuantity() > 3) {
            return product.calculateTotalPrice().multiply(BigDecimal.valueOf(0.9));
        } else {
            return product.calculateTotalPrice();
        }
    }
}
