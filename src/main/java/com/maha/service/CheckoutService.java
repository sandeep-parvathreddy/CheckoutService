package com.maha.service;

import com.maha.exception.ProductNotFoundException;
import com.maha.model.CheckoutResponse;

import java.util.List;

/**
 * Created by sandeepreddy on 06/03/21.
 */
public interface CheckoutService {

    CheckoutResponse checkout(List<String> productIds) throws ProductNotFoundException;
}
