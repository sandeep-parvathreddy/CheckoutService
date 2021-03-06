package com.maha.discounts;

import com.maha.discounts.strategy.UnitBasedDiscountStrategy;
import com.maha.model.Product;
import com.maha.model.ProductOrder;
import com.maha.model.discounts.ProductDiscount;
import com.maha.model.discounts.UnitsBasedDiscount;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sandeepreddy on 07/03/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UnitBasedDiscountStrategyTest {

    @InjectMocks
    UnitBasedDiscountStrategy unitBasedDiscountStrategy;

    Product product;

    ProductDiscount productDiscount;

    @Before
    public void setup(){
        product = Product.builder().price(100).build();
        productDiscount = UnitsBasedDiscount.builder().price(200).units(3).build();
    }

    @Test
    public void contextLoads()
    {

    }

    @Test
    public void test_single_discount(){
        ProductOrder productOrder = ProductOrder.builder().product(product).numOfUnits(3L).build();
        double discountedPrice = unitBasedDiscountStrategy.apply(productOrder,productDiscount);
        Assert.assertTrue(discountedPrice==100);

    }

    @Test
    public void test_multiple_discounts(){
        ProductOrder productOrder = ProductOrder.builder().product(product).numOfUnits(6L).build();
        double discountedPrice = unitBasedDiscountStrategy.apply(productOrder,productDiscount);
        Assert.assertTrue(discountedPrice==200);

    }

    @Test
    public void test_uneven_discounts(){
        ProductOrder productOrder = ProductOrder.builder().product(product).numOfUnits(7L).build();
        double discountedPrice = unitBasedDiscountStrategy.apply(productOrder,productDiscount);
        Assert.assertTrue(discountedPrice==200);

    }

    @Test
    public void test_discount_cannot_apply(){
        ProductOrder productOrder = ProductOrder.builder().product(product).numOfUnits(2L).build();
        double discountedPrice = unitBasedDiscountStrategy.apply(productOrder,productDiscount);
        Assert.assertTrue(discountedPrice==0);
    }

    @Test
    public void test_no_discount(){
        ProductOrder productOrder = ProductOrder.builder().product(product).numOfUnits(3L).build();
        productDiscount = UnitsBasedDiscount.builder().price(100).units(1).build();
        double discountedPrice = unitBasedDiscountStrategy.apply(productOrder,productDiscount);
        Assert.assertTrue(discountedPrice==0);
    }
}
