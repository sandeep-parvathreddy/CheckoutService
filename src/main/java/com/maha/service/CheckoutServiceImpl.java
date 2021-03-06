package com.maha.service;

import com.maha.model.CheckoutResponse;
import com.maha.model.ProductOrder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sandeepreddy on 06/03/21.
 */
@Service
@Log4j2
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    ProductOrderBuilderService productOrderBuilderService;

    @Override
    public CheckoutResponse checkout(List<String> productIds) {
        List<ProductOrder> productOrders = productOrderBuilderService.build(productIds);
        long actualPrice = productOrders.stream().mapToLong(ProductOrder::getActualPrice).sum();
        long discountedPrice = productOrders.stream().mapToLong(ProductOrder::getDiscountedPrice).sum();

        log.info("Calculated actualPrice is {} and  discountedPrice is {} ",actualPrice,discountedPrice);

        long finalPrice = actualPrice-discountedPrice;
        return CheckoutResponse.builder().price(finalPrice).build();
    }


}
