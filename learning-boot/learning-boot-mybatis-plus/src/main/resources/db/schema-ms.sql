DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    revision     INT       DEFAULT 1 COMMENT '乐观锁',
    created_by   VARCHAR(32) COMMENT '创建人',
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_by   VARCHAR(32) COMMENT '更新人',
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted      INT       DEFAULT 0 COMMENT '逻辑删除',
    name         VARCHAR(32) COMMENT '姓名',
    age          INT COMMENT '年龄',
    email        VARCHAR(32) COMMENT '邮箱',
    id           VARCHAR(64) NOT NULL COMMENT '主键',
    PRIMARY KEY (id)
) COMMENT = '用户表';
