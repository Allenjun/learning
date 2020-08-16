package com.allen.learningbootmybatis.mapper;

import com.allen.learningbootmybatis.pojo.DO.UserDO;
import com.allen.learningbootmybatis.pojo.sql.UserProvider;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

/**
 * @author JUN
 * @Description TODO
 * @createTime 13:46
 */
@Mapper
public interface UserMapper {
    
    Optional<UserDO> findById(@Param("id") String id);
    
    List<UserDO> findByName(@Param("name") String name);
    
    List<UserDO> findByCondition(UserDO user);
    
    List<UserDO> findListByIds(@Param("ids") List<Integer> ids);
    
    int updateById(@Param("id") Integer id, @Param("user") UserDO user);
    
    @Select("select * from t_user where id = #{id}")
    Optional<UserDO> findByIdAnno(@Param("id") String id);
    
    @SelectProvider(type = UserProvider.class)
    Optional<UserDO> findByConditionAnno(@Param("user") UserDO user);
    
    /**
     * 注意：方法返回的是影响记录数
     *      1. 数据库的主键生成策略是自增的（Mysql, SqlServer），可以用@Options
     *      2. 数据库的主键生成策略是序列的（pgsql）,可以用@SelectKey自定义sql返回语句
     */
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
//    @SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "id", keyColumn = "id", before = false, resultType = Integer.class)
    @InsertProvider(type = UserProvider.class)
    int insertOneAnno(@Param("user") UserDO user);
    
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    @InsertProvider(type = UserProvider.class)
    int insertAnno(@Param("users") List<UserDO> users);
}
