package com.allen.learningboottransaction.service;

import com.allen.learningboottransaction.pojo.DO.facebook.BookDO;
import com.allen.learningboottransaction.pojo.DO.facebook.UserDO;
import com.allen.learningboottransaction.pojo.DO.google.TeacherDO;
import com.allen.learningboottransaction.repository.facebook.BookRepository;
import com.allen.learningboottransaction.repository.facebook.UserRepository;
import com.allen.learningboottransaction.repository.google.TeacherRepository;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author admin
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019/07/17 11:52:00
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    PlatformTransactionManager platformTransactionManager;
    @Autowired
    @Qualifier("transactionTemplateGoogle")
    TransactionTemplate transactionTemplateGoogle;

    @Override
    @Transactional
    public void doInAnnoTransaction(BookDO bookDO, UserDO userDO) {
        bookRepository.save(bookDO);
        userRepository.save(userDO);
    }

    @Override
    @Transactional(transactionManager = "platformTransactionManagerGoogle")
    public void doInAnnoTransaction(TeacherDO teacherDO) {
        teacherRepository.save(teacherDO);
    }

    @Override
    public void doInCodeTransaction(BookDO bookDO, UserDO userDO) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(transactionDefinition);
        try {
            bookRepository.save(bookDO);
            userRepository.save(userDO);
            platformTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            platformTransactionManager.rollback(transactionStatus);
            throw e;
        }
    }

    @Override
    public void doInCodeTransaction(TeacherDO teacherDO) {
        transactionTemplateGoogle.execute(transactionStatus -> {
            try {
                teacherRepository.save(teacherDO);
                return null;
            } catch (Exception e) {
                transactionStatus.setRollbackOnly();
                throw e;
            }
        });
    }

    @Override
    @GlobalTransactional
    public void doInDistributedTransaction(BookDO bookDO, TeacherDO teacherDO) {

    }

    private void errorHere() {
        int i = 1 / 0;
    }
}
