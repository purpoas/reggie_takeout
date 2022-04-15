package com.itheima.reggie_takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie_takeout.common.R;
import com.itheima.reggie_takeout.entity.Employee;
import com.itheima.reggie_takeout.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author purpoas
 * @date 2022/4/14 15:31
 */
@Slf4j
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登陆
     * @param request 用于获取一个session
     * @param employee 员工对象
     * @return R
     */
    @PostMapping(value = "/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        //获取用户的登陆密码并进行md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //根据用户使用的登陆用户名查询该用户是否存在
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        //用户不存在
        if (emp == null) {
            return R.error("登陆失败");
        }
        //密码比对，若不一致则返回登陆失败
        if (!emp.getPassword().equals(password)) {
            return R.error("登陆失败");
        }
        //员工状态校验
        if (emp.getStatus() == 0) {
            return R.error("账号已禁用");
        }
        //登陆成功，将员工id存入Session并返回登陆成功结果，即员工对象
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);

    }


    /**
     * 员工退出
     * @param request 请求获取session
     * @return 字符串
     */
    @PostMapping(value = "/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }


    /**
     * 新增员工
     * @param employee 员工
     * @return 字符串
     */
    @PostMapping
    public R<String> add(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工，员工信息: {}", employee.toString());
        //密码默认为123456
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        Long empId = (Long) request.getSession().getAttribute("employee");
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);

        employeeService.save(employee);

        return R.success("新增员工成功");

    }


    @GetMapping(value = "/page")
    public R<Page<Employee>> page(int page, int pageSize, String name) {
        Page<Employee> iPage = new Page<>(page, pageSize);

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasLength(name), Employee::getName, name);
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        employeeService.page(iPage, queryWrapper);
        return R.success(iPage);
    }

}
