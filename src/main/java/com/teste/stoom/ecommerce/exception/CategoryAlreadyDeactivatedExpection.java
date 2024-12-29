package com.teste.stoom.ecommerce.exception;

public class CategoryAlreadyDeactivatedExpection extends RuntimeException {

    public CategoryAlreadyDeactivatedExpection(String id) {
        super("A categoria '" + id + "' já está desativada.");
    }
}
