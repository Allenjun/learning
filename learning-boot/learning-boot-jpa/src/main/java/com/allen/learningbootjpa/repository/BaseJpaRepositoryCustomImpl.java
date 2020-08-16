package com.allen.learningbootjpa.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JUN
 * @Description TODO
 * @createTime 13:46
 */
@Transactional(readOnly = true)
public class BaseJpaRepositoryCustomImpl<T, ID> extends SimpleJpaRepository<T, ID> implements
    BaseJpaRepositoryCustom<T> {
    
    EntityManager entityManager;
    
    public BaseJpaRepositoryCustomImpl(
        JpaEntityInformation<T, ?> entityInformation,
        EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }
    
    @Transactional
    @Override
    public <S extends T> List<S> saveAllNew(Iterable<S> entities) {
        ArrayList<S> list = new ArrayList<>();
        for (S s : entities) {
            list.add(saveNew(s));
        }
        return list;
    }
    
    @Transactional
    @Override
    public <S extends T> S saveNew(S var1) {
        entityManager.persist(var1);
        return var1;
    }
}
