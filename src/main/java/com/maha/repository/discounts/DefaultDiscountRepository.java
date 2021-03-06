package com.maha.repository.discounts;

import com.maha.model.Product;
import com.maha.model.discounts.ProductDiscount;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sandeepreddy on 06/03/21.
 */
@Repository
public class DefaultDiscountRepository implements DiscountRepository {

    private static Map<Product,ProductDiscount> productDiscounts = new HashMap<>();

    @PostConstruct
    void init(){

    }

    @Override
    public ProductDiscount getDiscount(Product product) {
        return productDiscounts.get(product);
    }
}
