package com.itheima.reggie_takeout.controller;

import com.itheima.reggie_takeout.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author purpoas
 * @date 2022/4/21 11:20
 */
@RestController
@RequestMapping(value = "/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @GetMapping
    public void page(int page, int pageSize, String name) {

    }
}
