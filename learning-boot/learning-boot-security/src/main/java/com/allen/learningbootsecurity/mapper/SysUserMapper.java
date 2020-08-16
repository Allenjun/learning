package com.allen.learningbootsecurity.mapper;

import com.allen.learningbootsecurity.pojo.DO.SysUser;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserMapper {
    
    @Select("select * from sys_user where user_name = #{userName}")
    @Results({
        @Result(id = true, column = "user_id", property = "userId")
    })
    Optional<SysUser> findByUserName(@Param("userName") String userName);
}