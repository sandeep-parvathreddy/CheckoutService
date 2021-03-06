package com.maha.discounts.strategy;

import com.maha.model.ProductOrder;
import com.maha.model.discounts.ProductDiscount;

/**
 * Created by sandeepreddy on 06/03/21.
 */
public interface DiscountStrategy {

    long apply(ProductOrder product, ProductDiscount productDiscount);
}
