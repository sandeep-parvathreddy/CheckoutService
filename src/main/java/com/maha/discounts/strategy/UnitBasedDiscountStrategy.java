package com.maha.discounts.strategy;

import com.maha.model.ProductOrder;
import com.maha.model.discounts.ProductDiscount;
import com.maha.model.discounts.UnitsBasedDiscount;
import org.springframework.stereotype.Service;

/**
 * Created by sandeepreddy on 06/03/21.
 */
@Service
public class UnitBasedDiscountStrategy implements DiscountStrategy {

    @Override
    public double apply(ProductOrder productOrder, ProductDiscount productDiscount) {
        UnitsBasedDiscount unitsBasedDiscount = (UnitsBasedDiscount)productDiscount;
        int discountAppliedTimes = (int) (productOrder.getNumOfUnits()/unitsBasedDiscount.getUnits());
        System.out.println(discountAppliedTimes);
        if(discountAppliedTimes==0){
            return 0.0;
        }
        return productOrder.getProduct().getPrice()*discountAppliedTimes*unitsBasedDiscount.getUnits() - unitsBasedDiscount.getPrice()*discountAppliedTimes;
    }
}
