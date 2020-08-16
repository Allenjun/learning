package com.allen.learningbootjpa.repository;

import com.allen.learningbootjpa.pojo.DO.CourseDO;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author JUN
 * @Description TODO
 * @createTime 11:41
 */
@Repository
public class CourseRepositoryCustomImpl implements CourseRepositoryCustom<CourseDO> {
    
    @Autowired
    EntityManager entityManager;
    
    @Override
    public <S extends CourseDO> S save(S var1) {
        System.out.println("进入自定义Repository方法");
        entityManager.persist(var1);
//        var1.setName("test");     // 此时的var已经是受管理状态，修改属性会影响数据库记录，这种状态在笨方法范围内有效
        return var1;
    }
}
