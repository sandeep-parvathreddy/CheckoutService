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

    private long numOfUnits;

    private long actualPrice;

    private long discountedPrice;

    public static class ProductOrderBuilder{
        public ProductOrderBuilder actualPrice(){
            this.actualPrice = (long)product.getPrice()*numOfUnits;
            return this;
        }
    }

}
