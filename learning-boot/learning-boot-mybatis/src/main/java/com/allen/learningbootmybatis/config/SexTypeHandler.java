package com.allen.learningbootmybatis.config;

import com.allen.learningbootmybatis.pojo.DO.Sex;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

/**
 * @author JUN
 * @Description TODO
 * @createTime 11:39
 */
@MappedJdbcTypes({ JdbcType.INTEGER })
@MappedTypes({ Sex.class })
public class SexTypeHandler implements TypeHandler<Sex> {
    
    @Override
    public void setParameter(PreparedStatement ps, int i, Sex parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.ordinal());
    }
    
    @Override
    public Sex getResult(ResultSet rs, String columnName) throws SQLException {
        return getResult(rs.getInt(columnName));
    }
    
    @Override
    public Sex getResult(ResultSet rs, int columnIndex) throws SQLException {
        return getResult(rs.getInt(columnIndex));
    }
    
    @Override
    public Sex getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getResult(cs.getInt(columnIndex));
    }
    
    private Sex getResult(int mark) throws SQLException {
        if (Sex.MALE.ordinal() == mark) {
            return Sex.MALE;
        } else if (Sex.FEMALE.ordinal() == mark) {
            return Sex.FEMALE;
        }
        throw new SQLException("参数转换异常");
    }
}
