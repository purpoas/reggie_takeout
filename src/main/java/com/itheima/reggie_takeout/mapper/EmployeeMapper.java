package com.itheima.reggie_takeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie_takeout.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author purpoas
 * @date 2022/4/14 15:26
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
