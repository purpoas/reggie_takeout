package com.itheima.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie_takeout.entity.Dish;
import com.itheima.reggie_takeout.mapper.DishMapper;
import com.itheima.reggie_takeout.service.DishService;
import org.springframework.stereotype.Service;

/**
 * @author purpoas
 * @date 2022/4/19 11:39
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
