package com.allen.learningbootsecurity.mapper;

import com.allen.learningbootsecurity.pojo.DO.SysRole;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysRoleMapper {
    
    @Select("select r.* from sys_user_role ur left join sys_role r on r.role_id = ur.role_id where ur.user_id = #{userId}")
    @Results({
        @Result(id = true, column = "role_id", property = "roleId"),
        @Result(column = "role_key", property = "roleKey")
    })
    List<SysRole> selectByUserId(@Param("userId") long userId);
    
}