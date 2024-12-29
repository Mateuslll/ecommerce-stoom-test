package com.teste.stoom.ecommerce.exception;

public class BrandAlreadyExistsException extends RuntimeException {
    public BrandAlreadyExistsException(String name) {
        super("A marca '" + name + "' já está registrada.");
    }
}
