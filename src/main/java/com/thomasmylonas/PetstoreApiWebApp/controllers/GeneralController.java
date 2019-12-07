package com.thomasmylonas.PetstoreApiWebApp.controllers;

public interface GeneralController<T> {
    T getById(long id);
    T postById(long id, T t);
    T deleteById(long id);
    T post(T t);
    T put(T t);
}
