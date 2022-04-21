package com.itheima.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie_takeout.dao.dto.DishDto;
import com.itheima.reggie_takeout.dao.entity.Dish;
import com.itheima.reggie_takeout.dao.entity.DishFlavor;
import com.itheima.reggie_takeout.mapper.DishMapper;
import com.itheima.reggie_takeout.service.DishFlavorService;
import com.itheima.reggie_takeout.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author purpoas
 * @date 2022/4/19 11:39
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品，同时保存对应的口味数据
     * @param dishDto dto
     */
    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {
        //保存菜品的基本信息到菜品表dish
        super.save(dishDto);
        //菜品id
        Long dishId = dishDto.getId();
        //菜品口味
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.stream().peek(item -> item.setDishId(dishId)).collect(Collectors.toList());
        //保存菜品口味到表dish_flavor
        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 根据id查询菜品的基本信息和口味信息
     * @param id id
     * @return DishDto
     */
    @Override
    public DishDto getByIdWithFlavor(Long id) {
        Dish dish = this.getById(id);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);

        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);

        dishDto.setFlavors(flavors);

        return dishDto;
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDto dishDto) {
        super.updateById(dishDto);

        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dishDto.getId());
        dishFlavorService.remove(queryWrapper);

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.stream().peek(item -> item.setDishId(dishDto.getId())).collect(Collectors.toList());

        dishFlavorService.saveBatch(flavors);
    }
}
