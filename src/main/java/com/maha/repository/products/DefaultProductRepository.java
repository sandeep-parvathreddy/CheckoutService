package com.maha.repository.products;

import com.maha.exception.ProductNotFoundException;
import com.maha.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Product getById(String productId){
        Optional<Product> product = productList.stream().filter(e -> e.getId().equals(productId)).findAny();
        if(product.isPresent()){
            return product.get();
        }
        else{
            throw new ProductNotFoundException("Product not found");
        }

    }

    private void createdProducts() {
        productList.add(Product.builder().id("001").name("Rolex").price(100).build());
        productList.add(Product.builder().id("002").name("Michael Kors").price(80).build());
        productList.add(Product.builder().id("003").name("Swatch").price(50).build());
        productList.add(Product.builder().id("004").name("Casio").price(30).build());
    }
}
