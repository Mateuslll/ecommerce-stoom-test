package com.teste.stoom.ecommerce.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String id) {
        super("Categoria com o ID '" + id + "' não foi encontrada.");
    }
}
