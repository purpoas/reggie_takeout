package com.itheima.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie_takeout.dao.entity.User;
import com.itheima.reggie_takeout.mapper.UserMapper;
import com.itheima.reggie_takeout.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author purpoas
 * @date 2022/4/22 11:47
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
