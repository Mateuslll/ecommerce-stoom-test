package com.teste.stoom.ecommerce.exception;

public class BrandAlreadyDeactivatedExpection extends RuntimeException {

    public BrandAlreadyDeactivatedExpection(String id) {
        super("A marca '" + id + "' já está desativada.");
    }
}
