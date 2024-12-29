package com.teste.stoom.ecommerce.exception;

public class BrandNotFoundException extends RuntimeException {

    public BrandNotFoundException(String id) {
        super("Marca com o ID '" + id + "' não foi encontrada.");
    }
}
