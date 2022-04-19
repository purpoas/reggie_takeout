package com.itheima.reggie_takeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie_takeout.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author purpoas
 * @date 2022/4/19 11:38
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
