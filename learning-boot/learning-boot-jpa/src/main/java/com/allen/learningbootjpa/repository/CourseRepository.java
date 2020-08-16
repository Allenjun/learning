package com.allen.learningbootjpa.repository;

import com.allen.learningbootjpa.pojo.DO.CourseDO;
import com.allen.learningbootjpa.pojo.DO.CourseInterf;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JUN
 * @Description TODO
 * @createTime 16:32
 */
public interface CourseRepository extends JpaRepository<CourseDO, Integer>, CourseRepositoryCustom<CourseDO> {
    
    @Query(value = "select c from CourseDO c where c.name = :name")
    List<CourseDO> selectWithAnno(@Param("name") String name);
    
    @Query(value = "select c from CourseDO c where c.name = :name")
    List<CourseDO> selectWithAnnoSort(@Param("name") String name, Sort sort);
    
    @Query(value = "select * from course where name = :name", nativeQuery = true)
    List<CourseDO> selectWithAnnoNative(@Param("name") String name);
    
    List<CourseDO> selectWithAnnoNameQuery(@Param("name") String name);
    
    Page<CourseDO> findByNameLikeOrderByIdDesc(String name, Pageable pageable);
    
    Optional<CourseDO> findFirstByIdEquals(Integer id);
    
    CourseInterf findTopByIdEquals(Integer id);
    
    @Modifying
    @Transactional
    @Query("update CourseDO set name = :name where id = :id")
    int update(@Param("name") String name, @Param("id") Integer id);
    
}
