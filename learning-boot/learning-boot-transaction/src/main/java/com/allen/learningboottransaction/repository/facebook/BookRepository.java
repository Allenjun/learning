package com.allen.learningboottransaction.repository.facebook;

import com.allen.learningboottransaction.pojo.DO.facebook.BookDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author admin
 * @version 1.0.0
 * @Description TODO
 * @createTime 2019/07/17 11:48:00
 */
@Repository
public interface BookRepository extends JpaRepository<BookDO, Integer> {

}
