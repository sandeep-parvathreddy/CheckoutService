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

    private double actualPrice;

    private double discountedPrice;

    public static class ProductOrderBuilder{
        public ProductOrderBuilder actualPrice(){
            this.actualPrice = product.getPrice()*numOfUnits;
            return this;
        }
    }

}
