package com.itheima.reggie_takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie_takeout.dao.dto.SetmealDto;
import com.itheima.reggie_takeout.dao.entity.Category;
import com.itheima.reggie_takeout.dao.entity.Setmeal;
import com.itheima.reggie_takeout.service.CategoryService;
import com.itheima.reggie_takeout.service.SetmealService;
import com.itheima.reggie_takeout.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author purpoas
 * @date 2022/4/21 11:20
 */
@RestController
@RequestMapping(value = "/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 套餐信息分页查询
     * @param page page
     * @param pageSize pageSize
     * @param name name
     * @return R
     */
    @GetMapping(value = "/page")
    public R<Page<SetmealDto>> page(int page, int pageSize, String name) {
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> dtoPage = new Page<>();

        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Setmeal::getName, name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        setmealService.page(pageInfo, queryWrapper);

        //对象拷贝,records不能拷贝因为泛型不匹配
        BeanUtils.copyProperties(pageInfo, dtoPage, "records");
        List<Setmeal> setmealRecords = pageInfo.getRecords();

        List<SetmealDto> list = setmealRecords.stream().map(item -> {

            SetmealDto setmealDto = new SetmealDto();

            BeanUtils.copyProperties(item, setmealDto);

            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;

        }).collect(Collectors.toList());

        dtoPage.setRecords(list);

        return R.success(dtoPage);
    }

    /**
     * 新增套餐
     * @param setmealDto dto
     * @return R
     */
    @PostMapping
    @Qualifier(value = "save")
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        setmealService.saveWithDish(setmealDto);
        return R.success("新增套餐成功");
    }

    /**
     * 删除套餐
     * @param ids ids
     * @return R
     */
    @DeleteMapping
    @Qualifier(value = "delete")
    public R<String> delete(@RequestParam(value = "ids") List<Long> ids) {
        setmealService.removeWithDish(ids);
        return R.success("删除套餐成功");
    }
    
}
