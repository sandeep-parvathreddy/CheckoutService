package com.maha.repository.discounts;

import com.maha.model.Product;
import com.maha.model.discounts.ProductDiscount;
import com.maha.model.discounts.UnitsBasedDiscount;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sandeepreddy on 06/03/21.
 */
@Repository
public class DefaultDiscountRepository implements DiscountRepository {

    private Map<String,ProductDiscount> productDiscountMap = new HashMap<>();

    @PostConstruct
    void init(){
        productDiscountMap.put("001",UnitsBasedDiscount.builder().price(200.0).units(3).build());
        productDiscountMap.put("002",UnitsBasedDiscount.builder().price(120.0).units(2).build());
    }

    @Override
    public ProductDiscount getDiscount(Product product) {
        return productDiscountMap.get(product.getId());
    }
}
