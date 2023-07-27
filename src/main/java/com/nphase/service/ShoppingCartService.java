package com.nphase.service;

import com.nphase.entity.Discount;
import com.nphase.entity.Product;
import com.nphase.entity.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCartService {

    //data should be fetched from db,
    private final Discount discount = new Discount(3, 3, BigDecimal.valueOf(0.9), BigDecimal.valueOf(0.9));

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

        if (categoryQuantity > discount.getCategoryDiscountRequiredAmount()) {
            categoryTotal = categoryTotal.multiply(discount.getCategoryDiscount());
        }

        return categoryTotal;
    }

    private BigDecimal calculateProductPrice(Product product) {
        if (product.getQuantity() > discount.getQuantityDiscountRequiredAmount()) {
            return product.calculateTotalPrice().multiply(discount.getQuantityDiscount());
        } else {
            return product.calculateTotalPrice();
        }
    }
}
