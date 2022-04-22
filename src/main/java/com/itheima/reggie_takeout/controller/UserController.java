package com.itheima.reggie_takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie_takeout.dao.entity.User;
import com.itheima.reggie_takeout.service.UserService;
import com.itheima.reggie_takeout.utils.R;
import com.itheima.reggie_takeout.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author purpoas
 * @date 2022/4/22 11:48
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        //获取手机号
        String phone = user.getPhone();
        if (!phone.isEmpty()) {
            //随机生成4为验证码
            String code = ValidateCodeUtils.generateValidateCode4String(4);
            log.info("code: {}", code);

            //调用阿里云提供的短信服务API完成发送短信
            //SMSUtils.sendMessage("瑞吉外卖", "", phone, code);

            //需要讲生成的验证码保存到session中
            session.setAttribute(phone, code);

            return R.success("手机验证码发送成功");
        }

        return R.error("短信发送失败");
    }

    /**
     * 移动端用户登陆
     * @param map map
     * @param session session
     * @return R
     */
    @PostMapping(value = "/login")
    public R<User> login(@RequestBody Map<String, String> map, HttpSession session) {
        log.info(map.toString());
        String phone = map.get("phone");
        String code = map.get("code");

        String codeInSession = (String) session.getAttribute(phone);
        if (codeInSession != null && codeInSession.equals(code)) {
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone, phone);
            User user = userService.getOne(queryWrapper);
            if (user == null) {
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            return R.success(user);
        }

        return R.error("登陆失败");
    }

}
