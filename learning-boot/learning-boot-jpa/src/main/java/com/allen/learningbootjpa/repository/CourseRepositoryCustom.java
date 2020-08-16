package com.allen.learningbootjpa.repository;

public interface CourseRepositoryCustom<T> {
    
    <S extends T> S save(S var1);
    
}
