package com.Zpher.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.Zpher.reggie.common.R;
import com.Zpher.reggie.entity.Employee;
import com.Zpher.reggie.service.EmployeeService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * ClassName: EmployeeController
 * Package: com.Zpher.reggie.controller
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

    @PostMapping
    private R<String> save(HttpServletRequest request, @RequestBody Employee emp) {

        log.info("新增员工,员工信息：", emp.toString());

        //补全emp的属性
        //设置初始密码

        emp.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        //设置创建时间和修改时间
        // emp.setCreateTime(LocalDateTime.now());
        // emp.setUpdateTime(LocalDateTime.now());

        //设置创建用户和修改用户
        // Long createUserID = (Long) request.getSession().getAttribute("employee");
        // emp.setCreateUser(createUserID);
        // emp.setUpdateUser(createUserID);
        //
        employeeService.save(emp);
        return R.success("新增员工成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {

        log.info("分页查询 page = {}, pageSize = {}, name = {}", page, pageSize, name);


        //创建分页构造器
        Page<Employee> pageInfo = new Page(page, pageSize);

        //创建条件构造器
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();

        //添加过滤条件
        lqw.like( name != null, Employee::getUsername, name);

        //添加排序条件
        lqw.orderByDesc(Employee::getUpdateTime);

        employeeService.page(pageInfo, lqw);
        return R.success(pageInfo);

    }


    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {


        long id = Thread.currentThread().getId();
        log.info("update中当前线程的id位{}", id);

        // employee.setUpdateTime(LocalDateTime.now());
        // employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }

    @GetMapping("/{userID}")
    public R<Employee> getById(@PathVariable Long userID) {

        log.info("查询员工，id={}", userID);
        Employee emp = employeeService.getById(userID);
        if( emp != null) {
            return R.success(emp);
        }
        return R.error("找不到对应员工");
    }


}
