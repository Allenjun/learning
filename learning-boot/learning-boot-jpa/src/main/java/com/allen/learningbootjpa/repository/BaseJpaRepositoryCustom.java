package com.allen.learningbootjpa.repository;

import java.util.List;

public interface BaseJpaRepositoryCustom<T> {
    
    <S extends T> List<S> saveAllNew(Iterable<S> entities);
    
    <S extends T> S saveNew(S var1);
}
