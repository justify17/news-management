package com.test.newsmanagement.service;

public interface DefaultService<T> {
    int DEFAULT_ELEMENTS_NUMBER_PER_PAGINATION_PAGE = 5;

    void save(T entityDto);

    T getById(Long id);

    void update(Long id, T entityDto);

    void deleteById(Long id);
}
