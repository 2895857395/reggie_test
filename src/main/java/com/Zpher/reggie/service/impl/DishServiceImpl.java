package com.Zpher.reggie.service.impl;

import com.Zpher.reggie.entity.Dish;
import com.Zpher.reggie.mapper.DishMapper;
import com.Zpher.reggie.service.DishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * ClassName: DishServceImpl
 * Package: com.Zpher.reggie.service.impl
 * Description:
 *
 * @Author WHU-PeterZhang
 * @Create 2024/8/9 14:56
 * @Version 1.0
 */
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
