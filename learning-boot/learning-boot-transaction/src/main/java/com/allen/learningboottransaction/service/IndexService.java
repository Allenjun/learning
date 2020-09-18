package com.allen.learningboottransaction.service;

import com.allen.learningboottransaction.pojo.DO.facebook.BookDO;
import com.allen.learningboottransaction.pojo.DO.facebook.UserDO;
import com.allen.learningboottransaction.pojo.DO.google.TeacherDO;

/**
 * @author admin
 * @version 1.0.0 @Description TODO
 * @createTime 2019/07/17 11:50:00
 */
public interface IndexService {

    /**
     * @description: 单个数据库事务
     */
    void doInAnnoTransaction(BookDO bookDO, UserDO userDO);

    /**
     * @description: 单个数据库事务
     */
    void doInAnnoTransaction(TeacherDO teacherDO);

    /**
     * @description: 单个数据库编程式事务
     */
    void doInCodeTransaction(BookDO bookDO, UserDO userDO);

    /**
     * @description: 单个数据库编程式事务
     */
    void doInCodeTransaction(TeacherDO teacherDO);

    /**
     * @description: 分布式事务
     */
    void doInDistributedTransaction(BookDO bookDO, TeacherDO teacherDO);
}
