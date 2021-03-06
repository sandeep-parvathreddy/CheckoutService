package com.maha.model.discounts;

import com.maha.utils.DiscountType;
import lombok.Builder;
import lombok.Data;

/**
 * Created by sandeepreddy on 06/03/21.
 */
@Data
@Builder
public class UnitsBasedDiscount extends ProductDiscount{

    private int units;

    private double price;

    @Override
    public DiscountType getDiscountType() {
        return DiscountType.UNIT_BASED_DISCOUNT;
    }
}
