package com.itheima.reggie_takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie_takeout.dao.dto.SetmealDto;
import com.itheima.reggie_takeout.dao.entity.Setmeal;

import java.util.List;

/**
 * @author purpoas
 * @date 2022/4/19 11:36
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto setmealDto
     */
    void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐，同时删除套餐和菜品的关联数据
     * @param ids ids
     */
    void removeWithDish(List<Long> ids);
}
