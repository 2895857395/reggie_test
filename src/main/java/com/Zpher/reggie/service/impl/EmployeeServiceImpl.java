package com.Zpher.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Zpher.reggie.entity.Employee;
import com.Zpher.reggie.mapper.EmployeeMapper;
import com.Zpher.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * ClassName: EmployeeServiceImpl
 * Package: com.Zpher.reggie.service.impl
 * Description:
 *
 * @Author WHU-PeterZhang
 * @Create 2024/8/7 15:03
 * @Version 1.0
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{

}
