package com.maha.model.discounts;

import com.maha.model.Product;
import com.maha.utils.DiscountType;

/**
 * Created by sandeepreddy on 06/03/21.
 */

public abstract class ProductDiscount {

    private Product product;

    public abstract DiscountType getDiscountType();

}
