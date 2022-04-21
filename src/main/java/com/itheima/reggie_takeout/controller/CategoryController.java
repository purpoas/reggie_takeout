package com.itheima.reggie_takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie_takeout.dao.entity.Category;
import com.itheima.reggie_takeout.service.CategoryService;
import com.itheima.reggie_takeout.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author purpoas
 * @date 2022/4/19 10:05
 */
@RestController
@Slf4j
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     *
     * @param category 菜品分类
     * @return 字符串
     */
    @PostMapping
    @Qualifier(value = "save")
    public R<String> save(@RequestBody Category category) {
        categoryService.save(category);
        return R.success("添加分类成功");
    }

    /**
     * 分类查询
     *
     * @param page     页面
     * @param pageSize 页面展示数
     * @return Page
     */
    @GetMapping(value = "/page")
    public R<Page<Category>> page(int page, int pageSize) {
        Page<Category> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);

        return R.success(categoryService.page(pageInfo, queryWrapper));
    }

    @DeleteMapping
    @Qualifier(value = "delete")
    public R<String> delete(Long id) {
        categoryService.remove(id);
        return R.success("删除分类信息成功");
    }

    @PutMapping
    @Qualifier(value = "update")
    public R<String> update(@RequestBody Category category) {
        categoryService.updateById(category);
        return R.success("修改分类信息成功");
    }

    @GetMapping(value = "/list")
    public R<List<Category>> list(Category category) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getType() != null, Category::getType, category.getType());
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(queryWrapper);

        return R.success(list);
    }
}
