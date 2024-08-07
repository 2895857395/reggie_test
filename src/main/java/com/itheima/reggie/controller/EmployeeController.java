package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.PseudoColumnUsage;

/**
 * ClassName: EmployeeController
 * Package: com.itheima.reggie.controller
 * Description:
 *
 * @Author WHU-PeterZhang
 * @Create 2024/8/7 15:05
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {

        //1,将页面提交的密码进行password进行MD5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);


        //如果没有查询到则返回登录失败
        if(emp == null) {
            return R.error("登录失败");
        }
        //如果查询到了则比对查询的密码的MD5值和页面提交的密码MD5值是否一致
        if(!emp.getPassword().equals(password)) {
            return R.error("密码错误");
        }

        //5,查看员工状态，如果为已禁用则返回员工已禁用
        if(emp.getStatus() == 0) {
            return R.error("员工已禁用");
        }
        //6,登陆成功,将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee", emp.getId());


        return R.success(emp);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {

        request.getSession().removeAttribute("employee");
        return R.success("退出成功");

    }
}
