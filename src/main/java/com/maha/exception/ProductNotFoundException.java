package com.maha.exception;

/**
 * Created by sandeepreddy on 07/03/21.
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message,Throwable cause) {
        super(message,cause);
    }


    public ProductNotFoundException(String message) {
        super(message);
    }

}
