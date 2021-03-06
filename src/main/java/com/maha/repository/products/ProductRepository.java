package com.maha.repository.products;

import com.maha.model.Product;

/**
 * Created by sandeepreddy on 06/03/21.
 */
public interface ProductRepository {

    Product getById(String productId);
}
