package com.maha.discounts;

import com.maha.model.ProductOrder;
import com.maha.model.discounts.ProductDiscount;
import com.maha.repository.discounts.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sandeepreddy on 06/03/21.
 */
@Service
public class ProductDiscountService {

    @Autowired
    DiscountRepository discountRepository;

    public void calculateAndSetDiscount(ProductOrder productOrder){

        double discountedPrice = calculateDiscount(productOrder);
        productOrder.setDiscountedPrice(discountedPrice);
    }

    private double calculateDiscount(ProductOrder productOrder) {
        ProductDiscount productDiscount = discountRepository.getDiscount(productOrder.getProduct());
        if(productDiscount==null){
            return 0.0;
        }
        return DiscountStrategyFactory.getDiscountStrategy(productDiscount.getDiscountType()).apply(productOrder,productDiscount);
    }


}
