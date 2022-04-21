package com.itheima.reggie_takeout.dao.dto;

import com.itheima.reggie_takeout.dao.entity.Dish;
import com.itheima.reggie_takeout.dao.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
