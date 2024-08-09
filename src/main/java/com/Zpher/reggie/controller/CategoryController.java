package com.Zpher.reggie.controller;

import com.Zpher.reggie.common.R;
import com.Zpher.reggie.entity.Category;
import com.Zpher.reggie.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.PrinterJob;

/**
 * ClassName: CategoryController
 * Package: com.Zpher.reggie.controller
 * Description:
 *
 * @Author WHU-PeterZhang
 * @Create 2024/8/8 21:21
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public R<String> save(@RequestBody Category category) {

        log.info("category：{}", category);
        categoryService.save(category);
        return R.success("新增分类成功");
    }

    // @GetMapping
    // public R<Category> getByID() {
    //
    // }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {

        Page<Category> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByAsc(Category::getSort);

        categoryService.page(pageInfo, lambdaQueryWrapper);

        return R.success(pageInfo);


    }

    @DeleteMapping
    public R<String> delete(Long ids) {
        log.info("删除id为{}的分类", ids);
        categoryService.removeById(ids);
        return R.success("删除成功");
    }
}
