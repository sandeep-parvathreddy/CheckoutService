package com.maha.discounts;

import com.maha.model.ProductOrder;
import com.maha.model.discounts.ProductDiscount;
import com.maha.repository.discounts.DiscountRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sandeepreddy on 06/03/21.
 */
@Service
@Log4j2
public class ProductDiscountService {

    @Autowired
    DiscountRepository discountRepository;

    public void calculateAndSetDiscount(ProductOrder productOrder) {

        long discountedPrice = calculateDiscount(productOrder);
        productOrder.setDiscountedPrice(discountedPrice);
    }

    private long calculateDiscount(ProductOrder productOrder) {
        ProductDiscount productDiscount = discountRepository.getDiscount(productOrder.getProduct());
        if (productDiscount == null) {
            log.info("No product discount for product with id {}. Hence not calculating", productOrder.getProduct().getId());
            return 0;
        }
        return DiscountStrategyFactory.getDiscountStrategy(productDiscount.getDiscountType()).apply(productOrder, productDiscount);
    }


}
