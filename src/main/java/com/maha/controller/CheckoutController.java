package com.maha.controller;

import com.maha.model.CheckoutResponse;
import com.maha.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sandeepreddy on 06/03/21.
 */
@RestController
@RequestMapping(value = "/checkout", produces = MediaType.APPLICATION_JSON_VALUE)
public class CheckoutController {

    @Autowired
    CheckoutService checkoutService;

    @PostMapping
    public ResponseEntity<CheckoutResponse> calculatePrice(
            @RequestBody List<String> productsIds) {
        CheckoutResponse response = checkoutService.checkout(productsIds);
        System.out.println(response.getPrice());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
