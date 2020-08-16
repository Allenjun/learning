package com.allen.learningbootsecurity.mapper;

import com.allen.learningbootsecurity.pojo.DO.SysMenu;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysMenuMapper {
    
    @Select("select m.* from sys_menu m, sys_role_menu rm where m.menu_id = rm.menu_id and rm.role_id = #{roleId}")
    @Results({
        @Result(id = true, column = "menu_id", property = "menuId"),
    })
    List<SysMenu> selectByRoleId(@Param("roleId") long roleId);
}