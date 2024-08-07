package com.itheima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: EmployeeMapper
 * Package: com.itheima.reggie.mapper
 * Description:
 *
 * @Author WHU-PeterZhang
 * @Create 2024/8/7 15:00
 * @Version 1.0
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
