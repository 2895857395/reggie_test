package com.Zpher.reggie.controller;

import com.Zpher.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: SetmealController
 * Package: com.Zpher.reggie.controller
 * Description:
 *
 * @Author WHU-PeterZhang
 * @Create 2024/8/9 15:05
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping
public class SetmealController {

    @Autowired
    private SetmealService setmealService;
}
