package com.maha.service;

import com.maha.discounts.ProductDiscountService;
import com.maha.model.Product;
import com.maha.model.ProductOrder;
import com.maha.repository.products.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sandeepreddy on 07/03/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductBuilderServiceTest {

    @InjectMocks
    ProductOrderBuilderService productOrderBuilderService;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductDiscountService productDiscountService;

    Product product;

    @Before
    public void setup(){
        product = Product.builder().price(100).build();
        Mockito.when(productRepository.getById("001")).thenReturn(Product.builder().price(100).build());
        Mockito.when(productRepository.getById("002")).thenReturn(Product.builder().price(80).build());
        Mockito.when(productRepository.getById("003")).thenReturn(Product.builder().price(50).build());
        Mockito.when(productRepository.getById("004")).thenReturn(Product.builder().price(30).build());

        Mockito.doNothing().when(productDiscountService).calculateAndSetDiscount(ProductOrder.builder().product(product).build());
    }

    @Test
    public void test_buildOneProductOrder(){
        List<ProductOrder> productOrderList = productOrderBuilderService.build(Arrays.asList("001"));
        ProductOrder expectedProductOrder = ProductOrder.builder().product(product).numOfUnits(1).build();
        expectedProductOrder.setActualPrice(100);
        Assert.assertEquals(expectedProductOrder, productOrderList.get(0));
    }

    @Test
    public void test_buildOneProductOrder_multipleQuantities(){
        List<ProductOrder> productOrderList = productOrderBuilderService.build(Arrays.asList("001","001","001"));
        ProductOrder expectedProductOrder = ProductOrder.builder().product(product).numOfUnits(3).build();
        expectedProductOrder.setActualPrice(300);
        Assert.assertEquals(Arrays.asList(expectedProductOrder), productOrderList);
    }

    @Test
    public void test_buildMultipleProductOrder_singleQuantity(){
        List<ProductOrder> productOrderList = productOrderBuilderService.build(Arrays.asList("001","002","003"));

        ProductOrder expectedProductOrder1 = ProductOrder.builder().product(product).numOfUnits(1).build();
        expectedProductOrder1.setActualPrice(100);
        ProductOrder expectedProductOrder2 = ProductOrder.builder().product(Product.builder().price(80).build()).numOfUnits(1).build();
        expectedProductOrder2.setActualPrice(80);
        ProductOrder expectedProductOrder3 = ProductOrder.builder().product(Product.builder().price(50).build()).numOfUnits(1).build();
        expectedProductOrder3.setActualPrice(50);

        Assert.assertEquals(Arrays.asList(expectedProductOrder1,expectedProductOrder2,expectedProductOrder3), productOrderList);
    }

    @Test
    public void test_buildMultipleProductOrder_multipleQuantity(){
        List<ProductOrder> productOrderList = productOrderBuilderService.build(Arrays.asList("001","001","002","002","003"));

        ProductOrder expectedProductOrder1 = ProductOrder.builder().product(product).numOfUnits(2).build();
        expectedProductOrder1.setActualPrice(200);
        ProductOrder expectedProductOrder2 = ProductOrder.builder().product(Product.builder().price(80).build()).numOfUnits(2).build();
        expectedProductOrder2.setActualPrice(160);
        ProductOrder expectedProductOrder3 = ProductOrder.builder().product(Product.builder().price(50).build()).numOfUnits(1).build();
        expectedProductOrder3.setActualPrice(50);

        Assert.assertEquals(Arrays.asList(expectedProductOrder1,expectedProductOrder2,expectedProductOrder3), productOrderList);
    }
}
