package com.itheima.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie_takeout.dao.entity.DishFlavor;
import com.itheima.reggie_takeout.mapper.DishFlavorMapper;
import com.itheima.reggie_takeout.service.DishFlavorService;
import org.springframework.stereotype.Service;

/**
 * @author purpoas
 * @date 2022/4/20 09:49
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
