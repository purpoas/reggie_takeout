package com.itheima.reggie_takeout.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie_takeout.entity.Employee;
import com.itheima.reggie_takeout.mapper.EmployeeMapper;
import com.itheima.reggie_takeout.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author purpoas
 * @date 2022/4/14 15:27
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
