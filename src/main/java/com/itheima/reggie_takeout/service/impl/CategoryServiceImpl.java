package com.itheima.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie_takeout.entity.Category;
import com.itheima.reggie_takeout.mapper.CategoryMapper;
import com.itheima.reggie_takeout.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * @author purpoas
 * @date 2022/4/19 10:03
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
