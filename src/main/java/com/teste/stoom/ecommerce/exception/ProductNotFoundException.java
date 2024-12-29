package com.teste.stoom.ecommerce.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String id) {
        super("O produto com o ID '" + id + "' não foi encontrada.");
    }
}
