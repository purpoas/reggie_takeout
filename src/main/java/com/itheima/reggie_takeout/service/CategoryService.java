package com.itheima.reggie_takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie_takeout.dao.entity.Category;

/**
 * @author purpoas
 * @date 2022/4/19 10:03
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
