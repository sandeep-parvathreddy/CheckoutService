package com.maha.integrationTests;

import com.maha.CheckoutServiceApplication;
import com.maha.model.CheckoutResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sandeepreddy on 06/03/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = CheckoutServiceApplication.class)
@AutoConfigureMockMvc
public class CheckoutControllerIntegrationTest {

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void checkPrice_singleProduct() throws Exception {
        ResponseEntity<CheckoutResponse> checkoutResponse = queryCheckoutController(Arrays.asList("001"));
        assertEquals(HttpStatus.OK,checkoutResponse.getStatusCode());
        assertEquals(100,checkoutResponse.getBody().getPrice());
    }

    @Test
    public void checkPrice_singleProduct_singleDiscount() throws Exception {
        ResponseEntity<CheckoutResponse> checkoutResponse = queryCheckoutController(Arrays.asList("001","001","001"));
        assertEquals(HttpStatus.OK,checkoutResponse.getStatusCode());
        assertEquals(200,checkoutResponse.getBody().getPrice());
    }

    @Test
    public void checkPrice_singleProduct_multipleDiscounts() throws Exception {
        ResponseEntity<CheckoutResponse> checkoutResponse = queryCheckoutController(Arrays.asList("001","001","001","001","001","001"));
        assertEquals(HttpStatus.OK,checkoutResponse.getStatusCode());
        assertEquals(400,checkoutResponse.getBody().getPrice());
    }

    @Test
    public void checkPrice_singleQuantity_eachProduct() throws Exception {
        ResponseEntity<CheckoutResponse> checkoutResponse = queryCheckoutController(Arrays.asList("001","002","003","004"));
        assertEquals(HttpStatus.OK,checkoutResponse.getStatusCode());
        assertEquals(260,checkoutResponse.getBody().getPrice());
    }

    @Test
    public void checkPrice_singleProduct_having_noDiscount() throws Exception {
        ResponseEntity<CheckoutResponse> checkoutResponse = queryCheckoutController(Arrays.asList("003"));
        assertEquals(HttpStatus.OK,checkoutResponse.getStatusCode());
        assertEquals(50, checkoutResponse.getBody().getPrice(),50);
    }

    @Test
    public void checkPrice_multipleProduct_having_noDiscount() throws Exception {
        ResponseEntity<CheckoutResponse> checkoutResponse = queryCheckoutController(Arrays.asList("003","003","003","003","003"));
        assertEquals(HttpStatus.OK,checkoutResponse.getStatusCode());
        assertEquals(250, checkoutResponse.getBody().getPrice());
    }

    @Test
    public void checkPrice_allProducts_having_oneDiscount() throws Exception {
        ResponseEntity<CheckoutResponse> checkoutResponse = queryCheckoutController(Arrays.asList("001","001","001","002","002"));
        assertEquals(HttpStatus.OK,checkoutResponse.getStatusCode());
        assertEquals(320, checkoutResponse.getBody().getPrice());
    }

    @Test
    public void checkPrice_allProducts_somehave_discounts() throws Exception {
        ResponseEntity<CheckoutResponse> checkoutResponse = queryCheckoutController(Arrays.asList("001","001","003","001","002","004","002"));
        assertEquals(HttpStatus.OK,checkoutResponse.getStatusCode());
        assertEquals(400, checkoutResponse.getBody().getPrice());
    }

    public ResponseEntity<CheckoutResponse> queryCheckoutController(List<String> productIds) throws Exception {
        HttpEntity<List<String>> requestEntity = new HttpEntity<>(productIds);
        ResponseEntity<CheckoutResponse> responseEntity = restTemplate.postForEntity("http://localhost:8080/checkout",requestEntity,CheckoutResponse.class);
        return responseEntity;
    }


}
