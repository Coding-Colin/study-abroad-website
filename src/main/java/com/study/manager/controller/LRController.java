package com.study.manager.controller;

import com.study.manager.bean.User;
import com.study.manager.mapper.UserMapper;
import com.study.manager.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class LRController {

    @Resource
    public UserMapper userMapper;

    /**
     * 跳转登陆页面
     * @return
     */
    @RequestMapping("/loginHtml.do")
    public String login(){
        return "login";
    }



    /**
     * 登陆验证
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/login.do")
    public String login(@RequestBody String array[],
                        HttpSession session){
        User user = userMapper.getByName(array[0]);//根据用户name查询用户
        if (user==null){
            return JsonUtil.toJson("当前用户没注册");
        }
        String pas = user.password;
        if(pas.equals(array[1])){
            if (Integer.parseInt(array[2])==user.pos){
                session.setAttribute("loginUser",array[0]);//把登陆用户设置到session中
                if(user.pos==1){
                    return JsonUtil.toJson("欢迎管理员登陆");//返回管理端
                }
                else {
                    return JsonUtil.toJson("欢迎用户登陆");//返回首页
                }
            }
            else {
                return JsonUtil.toJson("用户身份对应错误");//返回首页
            }

        }
        else {
            return JsonUtil.toJson("密码错误");
        }
    }

    /**
     * 跳转注册页面
     * @return
     */
    @RequestMapping("/registHtml.do")
    public String regist(){
        return "regist";
    }




    /**
     * 退出登陆
     * @param session
     * @return
     */
    @RequestMapping("/quit.do")
    public String back(HttpSession session){
        session.removeAttribute("loginUser");
        return "login";
    }
}
