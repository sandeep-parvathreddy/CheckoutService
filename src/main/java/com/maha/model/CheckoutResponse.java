package com.maha.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by sandeepreddy on 06/03/21.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CheckoutResponse {

    private double price;

}
