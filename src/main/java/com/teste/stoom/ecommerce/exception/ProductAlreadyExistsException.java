package com.teste.stoom.ecommerce.exception;

public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException(String name) {
        super("O produto '" + name + "' já está registrada.");
    }
}
