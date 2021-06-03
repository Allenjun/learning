package com.allen.learningbootmybatisplus.service;

import com.allen.learningbootmybatisplus.mapper.UserMapper;
import com.allen.learningbootmybatisplus.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
