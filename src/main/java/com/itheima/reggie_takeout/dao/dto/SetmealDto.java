package com.itheima.reggie_takeout.dao.dto;

import com.itheima.reggie_takeout.dao.entity.Setmeal;
import com.itheima.reggie_takeout.dao.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
