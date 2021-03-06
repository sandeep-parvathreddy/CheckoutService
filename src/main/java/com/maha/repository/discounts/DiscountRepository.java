package com.maha.repository.discounts;

import com.maha.model.Product;
import com.maha.model.discounts.ProductDiscount;

/**
 * Created by sandeepreddy on 06/03/21.
 */
public interface DiscountRepository {

    ProductDiscount getDiscount(Product product);
}
