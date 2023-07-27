package com.nphase.entity;

import java.math.BigDecimal;

public class Discount {
    private final int quantityDiscountRequiredAmount;
    private final int categoryDiscountRequiredAmount;
    private final BigDecimal categoryDiscount;
    private final BigDecimal quantityDiscount;

    public Discount(int quantityDiscountRequiredAmount, int categoryDiscountRequiredAmount, BigDecimal categoryDiscount, BigDecimal quantityDiscount) {
        this.quantityDiscountRequiredAmount = quantityDiscountRequiredAmount;
        this.categoryDiscountRequiredAmount = categoryDiscountRequiredAmount;
        this.categoryDiscount = categoryDiscount;
        this.quantityDiscount = quantityDiscount;
    }

    public int getQuantityDiscountRequiredAmount() {
        return quantityDiscountRequiredAmount;
    }

    public int getCategoryDiscountRequiredAmount() {
        return categoryDiscountRequiredAmount;
    }

    public BigDecimal getCategoryDiscount() {
        return categoryDiscount;
    }

    public BigDecimal getQuantityDiscount() {
        return quantityDiscount;
    }
}
