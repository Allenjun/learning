package com.allen.learningbootjpa.repository;

import com.allen.learningbootjpa.pojo.DO.BookDO;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends BaseJpaRepository<BookDO, Long> {
    
}
