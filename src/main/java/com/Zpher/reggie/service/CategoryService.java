package com.Zpher.reggie.service;

import com.Zpher.reggie.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * ClassName: CategoryService
 * Package: com.Zpher.reggie.service
 * Description:
 *
 * @Author WHU-PeterZhang
 * @Create 2024/8/8 21:02
 * @Version 1.0
 */

public interface CategoryService extends IService<Category> {

    public void remove(Long id);
}
