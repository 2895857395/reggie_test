package com.Zpher.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.Zpher.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: EmployeeMapper
 * Package: com.Zpher.reggie.mapper
 * Description:
 *
 * @Author WHU-PeterZhang
 * @Create 2024/8/7 15:00
 * @Version 1.0
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
