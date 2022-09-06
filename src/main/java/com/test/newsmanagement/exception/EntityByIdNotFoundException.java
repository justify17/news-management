package com.test.newsmanagement.exception;

public class EntityByIdNotFoundException extends RuntimeException {

    public EntityByIdNotFoundException(Class<?> entityClass, Long id) {
        super(String.format("%s with id %d does not exist", entityClass.getSimpleName(), id));
    }
}
