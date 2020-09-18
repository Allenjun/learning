package com.allen.learningbootsecurity.mapper;

import com.allen.learningbootsecurity.pojo.DO.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface SysUserMapper {

    @Select("select * from sys_user where user_name = #{userName}")
    @Results({@Result(id = true, column = "user_id", property = "userId")})
    Optional<SysUser> findByUserName(@Param("userName") String userName);
}
