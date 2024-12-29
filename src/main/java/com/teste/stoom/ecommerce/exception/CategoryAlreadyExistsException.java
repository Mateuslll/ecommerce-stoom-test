package com.teste.stoom.ecommerce.exception;

public class CategoryAlreadyExistsException extends RuntimeException{

    public CategoryAlreadyExistsException(String name) {
        super("A categoria '" + name + "' já está registrada.");
    }
}
