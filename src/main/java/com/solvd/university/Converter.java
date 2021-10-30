package com.solvd.university;

public interface Converter<T, R> {

    R convert(T t);
}
