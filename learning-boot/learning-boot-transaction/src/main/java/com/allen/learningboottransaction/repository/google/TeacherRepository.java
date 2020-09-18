package com.allen.learningboottransaction.repository.google;

import com.allen.learningboottransaction.pojo.DO.google.TeacherDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author admin
 * @version 1.0.0 @Description TODO
 * @createTime 2019/07/17 11:48:00
 */
@Repository
public interface TeacherRepository extends JpaRepository<TeacherDO, Integer> {

}
