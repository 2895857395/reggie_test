package com.Zpher.reggie.controller;

import com.Zpher.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: DishController
 * Package: com.Zpher.reggie.controller
 * Description:
 *
 * @Author WHU-PeterZhang
 * @Create 2024/8/9 15:03
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping
public class DishController {
    @Autowired
    private DishService dishService;
}
