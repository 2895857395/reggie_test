package com.Zpher.reggie.service.impl;

import com.Zpher.reggie.entity.Category;
import com.Zpher.reggie.entity.Dish;
import com.Zpher.reggie.entity.Setmeal;
import com.Zpher.reggie.mapper.CategoryMapper;
import com.Zpher.reggie.service.CategoryService;
import com.Zpher.reggie.service.SetmealService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: CategoryServiceImpl
 * Package: com.Zpher.reggie.service.impl
 * Description:
 *
 * @Author WHU-PeterZhang
 * @Create 2024/8/8 21:03
 * @Version 1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishServiceImpl dishService;

    @Autowired
    private SetmealServiceImpl setmealService;

    /**
     * 根据ID删除分类，删除之前先进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {

        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Dish::getCategoryId, id);

        //先查询菜品表,如果有关联的菜品则抛出异常

        if(dishService.count(lambdaQueryWrapper) > 0) {
            return;
        }
        //然后查询套餐表,如果有关联的套餐同样抛出异常

        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.eq(Setmeal::getCategoryId, id);
        if (setmealService.count(lambdaQueryWrapper1) > 0) {
            return;
        }

        //没有关联的表，删除该分类
        super.removeById(id);
    }
}
