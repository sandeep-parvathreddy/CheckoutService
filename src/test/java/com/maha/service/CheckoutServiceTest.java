package com.maha.service;

import com.maha.model.CheckoutResponse;
import com.maha.model.Product;
import com.maha.model.ProductOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sandeepreddy on 07/03/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CheckoutServiceTest {

    @InjectMocks
    CheckoutServiceImpl checkoutService;

    @Mock
    ProductOrderBuilderService productOrderBuilderService;

    Product product;


    @Before
    public void dataSetup(){
        product = Product.builder().price(100).build();

    }

    @Test
    public void contextLoads()
    {

    }

    @Test
    public void test_checkout_singleProduct(){
        List<String> productIds = Arrays.asList("001");

        List<ProductOrder> productOrderList = Arrays.asList(ProductOrder.builder().product(product).numOfUnits(1).actualPrice().build());
        Mockito.when(productOrderBuilderService.build(productIds)).thenReturn(productOrderList);
        CheckoutResponse checkoutResponse = checkoutService.checkout(productIds);
        assertEquals(100, checkoutResponse.getPrice());
    }

    @Test
    public void test_checkout_singleProduct_multiple_quantites(){
        List<String> productIds = Arrays.asList("001","001","001","001");

        List<ProductOrder> productOrderList = Arrays.asList(ProductOrder.builder().product(product).numOfUnits(4).actualPrice().build());
        Mockito.when(productOrderBuilderService.build(productIds)).thenReturn(productOrderList);
        CheckoutResponse checkoutResponse = checkoutService.checkout(productIds);
        assertEquals(400, checkoutResponse.getPrice());
    }

    @Test
    public void test_checkout_singleProduct_multiple_quantites_withdiscount(){
        List<String> productIds = Arrays.asList("001","001","001","001");

        List<ProductOrder> productOrderList = Arrays.asList(ProductOrder.builder().product(product).numOfUnits(4).discountedPrice(100).actualPrice().build());
        Mockito.when(productOrderBuilderService.build(productIds)).thenReturn(productOrderList);
        CheckoutResponse checkoutResponse = checkoutService.checkout(productIds);
        assertEquals(300, checkoutResponse.getPrice());
    }

    @Test
    public void test_checkout_eachProduct_single_quantity(){
        List<String> productIds = Arrays.asList("001","002","003");

        List<ProductOrder> productOrderList = new ArrayList<>();
        productOrderList.add(ProductOrder.builder().product(product).numOfUnits(1).actualPrice().build());
        productOrderList.add(ProductOrder.builder().product(Product.builder().price(80).build()).numOfUnits(1).actualPrice().build());
        productOrderList.add(ProductOrder.builder().product(Product.builder().price(50).build()).numOfUnits(1).actualPrice().build());
        Mockito.when(productOrderBuilderService.build(productIds)).thenReturn(productOrderList);

        CheckoutResponse checkoutResponse = checkoutService.checkout(productIds);
        assertEquals(230, checkoutResponse.getPrice());
    }

    @Test
    public void test_checkout_eachProduct_multiple_quantities(){
        List<String> productIds = Arrays.asList("001","002","001","002","003");

        List<ProductOrder> productOrderList = new ArrayList<>();
        productOrderList.add(ProductOrder.builder().product(product).numOfUnits(2).actualPrice().build());
        productOrderList.add(ProductOrder.builder().product(Product.builder().price(80).build()).numOfUnits(2).actualPrice().build());
        productOrderList.add(ProductOrder.builder().product(Product.builder().price(50).build()).numOfUnits(1).actualPrice().build());
        Mockito.when(productOrderBuilderService.build(productIds)).thenReturn(productOrderList);

        CheckoutResponse checkoutResponse = checkoutService.checkout(productIds);
        assertEquals(410, checkoutResponse.getPrice());
    }


    @Test
    public void test_checkout_eachProduct_multiple_quantities_with_discount(){
        List<String> productIds = Arrays.asList("001","002","001","001","002","003");

        List<ProductOrder> productOrderList = new ArrayList<>();
        productOrderList.add(ProductOrder.builder().product(product).numOfUnits(3).discountedPrice(100).actualPrice().build());
        productOrderList.add(ProductOrder.builder().product(Product.builder().price(80).build()).numOfUnits(2).discountedPrice(40).actualPrice().build());
        productOrderList.add(ProductOrder.builder().product(Product.builder().price(50).build()).numOfUnits(1).actualPrice().build());
        Mockito.when(productOrderBuilderService.build(productIds)).thenReturn(productOrderList);

        CheckoutResponse checkoutResponse = checkoutService.checkout(productIds);
        assertEquals(370, checkoutResponse.getPrice());
    }



}
