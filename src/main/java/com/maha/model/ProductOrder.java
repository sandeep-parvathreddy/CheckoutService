package com.maha.model;

import lombok.Builder;
import lombok.Data;

/**
 * Created by sandeepreddy on 06/03/21.
 */
@Builder
@Data
public class ProductOrder {

    private Product product;

    private Long numOfUnits;

    private double totalPrice;

    private double discountedPrice;

    private void setTotalPrice(double totalPrice) {
        this.totalPrice = product.getPrice()*numOfUnits;
    }

}
