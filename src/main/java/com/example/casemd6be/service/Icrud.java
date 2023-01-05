package com.example.casemd6be.service;

import com.example.casemd6be.model.Product;

import java.util.List;

public interface Icrud<E> {
    void save(E e);

    List<E> getAll();
    void Delete(long id);
    E finByID(long id);
}
