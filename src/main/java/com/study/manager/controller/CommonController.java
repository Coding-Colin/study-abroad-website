package com.study.manager.controller;

import com.study.manager.bean.User;
import com.study.manager.mapper.UserMapper;
import com.study.manager.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class CommonController {

    @Resource
    public UserMapper userMapper;



    /**
     * 更新用户
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateUser.do")
    public String updateUser(@RequestBody String array[]){
        User user = userMapper.getById(Integer.parseInt(array[0]));
        user.setName(array[1]);
        user.setTel(array[2]);
        user.setPassword(array[3]);
        user.setPos(Integer.parseInt(array[4]));//设置为普通用户
        userMapper.update(user);
        return JsonUtil.toJson("更新成功");//返回登陆页面
    }



}
