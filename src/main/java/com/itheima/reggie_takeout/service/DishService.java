package com.itheima.reggie_takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie_takeout.dao.dto.DishDto;
import com.itheima.reggie_takeout.dao.entity.Dish;

/**
 * @author purpoas
 * @date 2022/4/19 11:39
 */
public interface DishService extends IService<Dish> {
    void saveWithFlavor(DishDto dishDto);

    DishDto getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDto dishDto);
}
