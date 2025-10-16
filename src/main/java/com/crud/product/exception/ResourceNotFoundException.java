package com.crud.product.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Object id) {

        super(id+ " not found");

    }
}
