package com.allen.learningbootmybatis.pojo.sql;

import com.allen.learningbootmybatis.pojo.DO.Sex;
import com.allen.learningbootmybatis.pojo.DO.UserDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author JUN @Description TODO
 * @createTime 15:38
 */
public class UserProvider implements ProviderMethodResolver {

    public static String findByConditionAnno(@Param("user") UserDO user) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM("t_user");
        if (Objects.nonNull(user.getId())) {
            sql.WHERE("id = #{user.id}");
        } else {
            if (Objects.nonNull(user.getName())) {
                sql.WHERE("name like #{user.name}");
            }
            if (Objects.nonNull(user.getPassword())) {
                sql.WHERE("password = #{user.password}");
            }
        }
        return sql.toString();
    }

    public static String insertOneAnno(@Param("user") UserDO user) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_user")
                .VALUES("name, password, sex", "#{user.name}, #{user.password}, #{user.sex}");
        return sql.toString();
    }

    public static String insertAnno(@Param("users") List<UserDO> users) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_user").INTO_COLUMNS("name", "password", "sex");
        String join =
                String.join(
                        ",",
                        users.stream()
                                .map(
                                        user ->
                                                "("
                                                        + (Objects.isNull(user.getName()) ? "null"
                                                        : "'" + user.getName() + "'")
                                                        + ","
                                                        + (Objects.isNull(user.getPassword())
                                                        ? "null"
                                                        : "'" + user.getPassword() + "'")
                                                        + ", "
                                                        + (Objects.isNull(user.getSex())
                                                        ? Sex.MALE.ordinal()
                                                        : user.getSex().ordinal())
                                                        + ")")
                                .collect(Collectors.toList()));
        return sql.toString() + " VALUES" + join;
    }
}
