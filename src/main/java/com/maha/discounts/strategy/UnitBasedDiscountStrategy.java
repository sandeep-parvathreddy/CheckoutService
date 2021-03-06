package com.maha.discounts.strategy;

import com.maha.model.ProductOrder;
import com.maha.model.discounts.ProductDiscount;
import com.maha.model.discounts.UnitsBasedDiscount;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Created by sandeepreddy on 06/03/21.
 */
@Service
@Log4j2
public class UnitBasedDiscountStrategy implements DiscountStrategy {

    @Override
    public long apply(ProductOrder productOrder, ProductDiscount productDiscount) {
        UnitsBasedDiscount unitsBasedDiscount = (UnitsBasedDiscount) productDiscount;
        int discountAppliedTimes = (int) (productOrder.getNumOfUnits() / unitsBasedDiscount.getUnits());
        log.info("Discount will be applied for {} times ", discountAppliedTimes);
        if (discountAppliedTimes == 0) {
            return 0;
        }
        return (long) (productOrder.getProduct().getPrice() * discountAppliedTimes * unitsBasedDiscount.getUnits() - unitsBasedDiscount.getPrice() * discountAppliedTimes);
    }
}
