package com.allen.learningbootmybatis;

import com.allen.learningbootmybatis.mapper.UserMapper;
import com.allen.learningbootmybatis.pojo.DO.Sex;
import com.allen.learningbootmybatis.pojo.DO.UserDO;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author JUN @Description TODO
 * @createTime 10:58
 */
public class AppTests {

    @Test
    public void test1() {
        // 1. 使用XMLConfigBuilder读取mybatis-config.xm配置文件
        XMLConfigBuilder xmlConfigBuilder =
                new XMLConfigBuilder(this.getClass().getResourceAsStream("/mybatis.xml"));
        // 2. 解析mybatis-config.xml --> Configuration
        Configuration configuration = xmlConfigBuilder.parse();
        // 3. 创建SqlSessionFactory
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        // 4. 获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 使用statemant查询
        List<Object> objects =
                sqlSession
                        .selectList("com.allen.learningbootmybatis.mapper.UserMapper.findByIdAnno", "1");
        System.out.println(objects);
        // 使用Mapper查询
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(mapper.findById("1"));

        UserDO add = new UserDO();
        add.setName("asddasfasfa");
        add.setPassword("654841");
        add.setSex(Sex.MALE);
        mapper.insertOneAnno(add);
        //        sqlSession.commit();

        //        sqlSession.close();
    }

    @Test
    public void test2() {
        try {
            Connection connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true",
                            "root",
                            "root");
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
