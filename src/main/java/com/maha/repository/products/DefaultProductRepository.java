package com.maha.repository.products;

import com.maha.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandeepreddy on 06/03/21.
 */
@Repository
public class DefaultProductRepository implements ProductRepository {

    private List<Product> productList = new ArrayList<>();

    @PostConstruct
    void init(){
        createdProducts();
    }

    @Override
    public Product getById(String productId) {
        return productList.stream().filter(e -> e.getId().equals(productId)).findFirst().get();
    }

    private void createdProducts() {
        productList.add(Product.builder().id("001").name("Rolex").price(100.0).build());
        productList.add(Product.builder().id("002").name("Michael Kors").price(80.0).build());
        productList.add(Product.builder().id("003").name("Swatch").price(50.0).build());
        productList.add(Product.builder().id("004").name("Casio").price(30.0).build());
    }
}
