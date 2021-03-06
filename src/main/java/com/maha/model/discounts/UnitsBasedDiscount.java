package com.maha.model.discounts;

import com.maha.utils.DiscountType;

/**
 * Created by sandeepreddy on 06/03/21.
 */
public class UnitsBasedDiscount extends ProductDiscount{

    private int units;

    private int price;

    @Override
    public DiscountType getDiscountType() {
        return DiscountType.UNIT_BASED_DISCOUNT;
    }
}
