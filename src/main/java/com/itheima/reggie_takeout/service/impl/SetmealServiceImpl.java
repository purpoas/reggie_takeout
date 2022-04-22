package com.itheima.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie_takeout.dao.dto.SetmealDto;
import com.itheima.reggie_takeout.dao.entity.Setmeal;
import com.itheima.reggie_takeout.dao.entity.SetmealDish;
import com.itheima.reggie_takeout.mapper.SetmealMapper;
import com.itheima.reggie_takeout.service.SetmealDishService;
import com.itheima.reggie_takeout.service.SetmealService;
import com.itheima.reggie_takeout.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author purpoas
 * @date 2022/4/19 11:37
 */
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDto setmealDto
     */
    @Override
    public void saveWithDish(SetmealDto setmealDto) {
        //保存套餐的基本信息，操作setmeal，执行insert操作
        this.save(setmealDto);

        //遍历出套餐中菜品，将其赋予其所属的套餐id（setmeal_id）
        List<SetmealDish> dishes = setmealDto.getSetmealDishes();
        List<SetmealDish> setMealDishes = dishes.stream()
                .peek(item -> item.setSetmealId(setmealDto.getId()))
                .collect(Collectors.toList());

        //保存套餐和菜品的关联信息，操作setmeal_dish表单，执行setmeal操作
        setmealDishService.saveBatch(setMealDishes);
    }

    @Transactional
    @Override
    public void removeWithDish (List<Long> ids) {
        //查询套餐状态，是否可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.in(Setmeal::getId, ids);
        queryWrapper1.eq(Setmeal::getStatus, 1);

        //若不能删除，抛出一个业务异常
        long count = this.count(queryWrapper1);
        if (count > 0) {
            throw new CustomException("套餐处于售卖状态，无法删除");
        }

        //若可删除，先删除套餐表中的数据,dish
        this.removeBatchByIds(ids);

        //删除关系表中的数据,dish_meal
        LambdaQueryWrapper<SetmealDish> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.in(SetmealDish::getSetmealId, ids);

        setmealDishService.remove(queryWrapper2);
    }

}
