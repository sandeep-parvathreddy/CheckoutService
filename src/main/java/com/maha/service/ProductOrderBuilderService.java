package com.maha.service;

import com.maha.discounts.ProductDiscountService;
import com.maha.model.Product;
import com.maha.model.ProductOrder;
import com.maha.repository.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by sandeepreddy on 07/03/21.
 */
@Service
public class ProductOrderBuilderService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductDiscountService productDiscountService;

    public List<ProductOrder> build(List<String> productIds) {
        Map<String,Long> productsCount = calculateUniqueProductCount(productIds);
        return productsCount.keySet().stream().map(productId -> buildProductOrder(productId,productsCount.get(productId))).collect(Collectors.toList());
    }

    private ProductOrder buildProductOrder(String productId, Long productCount) {
        Product product = productRepository.getById(productId);
        ProductOrder productOrder = ProductOrder.builder().product(product).numOfUnits(productCount).actualPrice().build();
        productDiscountService.calculateAndSetDiscount(productOrder);
        return productOrder;
    }

    private static Map<String,Long> calculateUniqueProductCount(List<String> productIds) {
        return productIds.stream().collect(Collectors.groupingBy(Function.identity(),
                Collectors.counting()));
    }
}
