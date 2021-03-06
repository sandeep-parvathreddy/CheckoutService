package com.maha.service;

import com.maha.discounts.ProductDiscountService;
import com.maha.exception.ProductNotFoundException;
import com.maha.model.CheckoutResponse;
import com.maha.model.Product;
import com.maha.model.ProductOrder;
import com.maha.repository.products.ProductRepository;
import com.maha.service.CheckoutService;
import com.maha.service.CheckoutServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * Created by sandeepreddy on 07/03/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CheckoutServiceTest {

    @InjectMocks
    CheckoutServiceImpl checkoutService;

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductDiscountService productDiscountService;

    @Before
    public void dataSetup(){
        Product product1 = Product.builder().price(100).build();
        Mockito.when(productRepository.getById("001")).thenReturn(Product.builder().price(100).build());
        Mockito.when(productRepository.getById("002")).thenReturn(Product.builder().price(80).build());
        Mockito.when(productRepository.getById("003")).thenReturn(Product.builder().price(50).build());
        Mockito.when(productRepository.getById("004")).thenReturn(Product.builder().price(30).build());

        Mockito.doNothing().when(productDiscountService).calculateAndSetDiscount(ProductOrder.builder().product(product1).build());
    }

    @Test
    public void contextLoads()
    {

    }

    @Test
    public void test_checkout_singleProduct(){
        CheckoutResponse checkoutResponse = checkoutService.checkout(Arrays.asList("001"));
        Assert.assertTrue(checkoutResponse.getPrice()==100);
    }

    @Test
    public void test_checkout_singleProduct_multiple_quantites(){
        CheckoutResponse checkoutResponse = checkoutService.checkout(Arrays.asList("001","001","001","001"));
        Assert.assertTrue(checkoutResponse.getPrice()==400);
    }

    @Test
    public void test_checkout_eachProduct_single_quantity(){
        CheckoutResponse checkoutResponse = checkoutService.checkout(Arrays.asList("001","002","003","004"));
        Assert.assertTrue(checkoutResponse.getPrice()==260);
    }

    @Test
    public void test_checkout_eachProduct_multiple_quantities(){
        CheckoutResponse checkoutResponse = checkoutService.checkout(Arrays.asList("001","002","001","002","003","004","004"));
        Assert.assertTrue(checkoutResponse.getPrice()==470);
    }



}
