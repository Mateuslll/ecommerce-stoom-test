package com.teste.stoom.ecommerce.exception;

public class ProductAlreadyDeactivatedExpection extends RuntimeException {

    public ProductAlreadyDeactivatedExpection(String id) {
        super("O Produto '" + id + "' já está desativado.");
    }
}
