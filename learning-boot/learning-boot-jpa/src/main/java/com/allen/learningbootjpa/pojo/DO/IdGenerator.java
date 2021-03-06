package com.allen.learningbootjpa.pojo.DO;

import com.allen.learningbootjpa.utils.SnowFlake;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * @author JUN @Description TODO
 * @createTime 12:33
 */
public class IdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object)
            throws HibernateException {
        return SnowFlake.nextId();
    }
}
