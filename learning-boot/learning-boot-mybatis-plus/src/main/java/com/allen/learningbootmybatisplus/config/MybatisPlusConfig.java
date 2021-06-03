package com.allen.learningbootmybatisplus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return mybatisPlusInterceptor;
    }

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MyMetaObjectHandler();
    }

    public static class MyMetaObjectHandler implements MetaObjectHandler {

        @Override
        public void insertFill(MetaObject metaObject) {
            this.strictInsertFill(metaObject, "createdTime", () -> LocalDateTime.now(), LocalDateTime.class);
            this.strictInsertFill(metaObject, "createdBy", () -> "user", String.class);
            this.strictInsertFill(metaObject, "updatedTime", () -> LocalDateTime.now(), LocalDateTime.class);
            this.strictInsertFill(metaObject, "updatedBy", () -> "user", String.class);
            this.strictInsertFill(metaObject, "deleted", () -> 0, Integer.class);

        }

        @Override
        public void updateFill(MetaObject metaObject) {
            this.strictUpdateFill(metaObject, "updatedTime", () -> LocalDateTime.now(), LocalDateTime.class);
            this.strictUpdateFill(metaObject, "updatedBy", () -> "user", String.class);
        }
    }
}
