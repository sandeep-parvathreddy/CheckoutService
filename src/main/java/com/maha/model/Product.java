package com.maha.model;

import lombok.Builder;
import lombok.Data;

/**
 * Created by sandeepreddy on 06/03/21.
 */
@Builder
@Data
public class Product {

    private String id;

    private String name;

    private double price;
}

