package com.allen.learningbootsecurity.mapper;

import com.allen.learningbootsecurity.pojo.DO.SysMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SysMenuMapper {

    @Select(
            "select m.* from sys_menu m, sys_role_menu rm where m.menu_id = rm.menu_id and rm.role_id = #{roleId}")
    @Results({
            @Result(id = true, column = "menu_id", property = "menuId"),
    })
    List<SysMenu> selectByRoleId(@Param("roleId") long roleId);
}
