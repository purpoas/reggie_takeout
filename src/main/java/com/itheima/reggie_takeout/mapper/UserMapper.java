package com.itheima.reggie_takeout.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.reggie_takeout.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author purpoas
 * @date 2022/4/22 11:46
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
