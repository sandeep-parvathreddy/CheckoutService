package com.maha.discounts;

import com.maha.discounts.strategy.DiscountStrategy;
import com.maha.discounts.strategy.UnitBasedDiscountStrategy;
import com.maha.utils.DiscountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sandeepreddy on 06/03/21.
 */
@Component
public class DiscountStrategyFactory {

    private static Map<DiscountType, DiscountStrategy> discountStrategyMap = new HashMap<>();

    @Autowired
    public UnitBasedDiscountStrategy unitBasedDiscountStrategy;

    @PostConstruct
    void init(){
        discountStrategyMap.put(DiscountType.UNIT_BASED_DISCOUNT, unitBasedDiscountStrategy);
    }


    public static DiscountStrategy getDiscountStrategy(DiscountType type) {
        return discountStrategyMap.get(type);
    }
}
