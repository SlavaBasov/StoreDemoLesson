package com.example.dao;

import com.example.entity.User;

import java.util.List;

public interface BaseDAO<T> {
    T add(T t);
    T findById(int id);
}
