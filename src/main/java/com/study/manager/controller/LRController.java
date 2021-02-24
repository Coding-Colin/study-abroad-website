package com.study.manager.controller;

import com.study.manager.bean.User;
import com.study.manager.mapper.UserMapper;
import com.study.manager.util.JsonUtil;
import com.study.manager.util.SendEmail;
import com.study.manager.util.UUIDUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class LRController {

    @Resource
    public UserMapper userMapper;

    /**
     * 跳转登陆页面
     *
     * @return
     */
    @RequestMapping("/loginHtml.do")
    public String login() {
        return "login";
    }


    /**
     * 登陆验证
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/login.do")
    public String login(@RequestBody String array[],
                        HttpSession session) {
        User user = userMapper.getByName(array[0]);//根据用户name查询用户
        if (user == null) {
            return JsonUtil.toJson("当前用户没注册");
        }
        String pas = user.password;
        if (pas.equals(array[1])) {
            if (Integer.parseInt(array[2]) == user.pos) {
                userMapper.updateLoginCount(user.name, user.getLoginCount() + 1L);
                session.setAttribute("loginUser", array[0]);//把登陆用户设置到session中
                if (user.pos == 1) {
                    return JsonUtil.toJson("欢迎管理员登陆");//返回管理端
                } else {
                    return JsonUtil.toJson("欢迎用户登陆");//返回首页
                }
            } else {
                return JsonUtil.toJson("用户身份对应错误");//返回首页
            }

        } else {
            return JsonUtil.toJson("密码错误");
        }
    }

    /**
     * 跳转注册页面
     *
     * @return
     */
    @RequestMapping("/registHtml.do")
    public String regist() {
        return "regist";
    }


    /**
     * 发送验证码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/senduuid.do", method = RequestMethod.POST)
    public String senduuid(@RequestBody String array[], HttpSession session) {
        String uuid = UUIDUtils.postUUID();
        session.setAttribute("uuid", uuid);
        try {
            SendEmail sendMail = new SendEmail();
            sendMail.setSmtpServer("smtp.qq.com");
            sendMail.setUsername("991386539@qq.com");
            sendMail.setPassword("pnnyysznkroobbdb");
            sendMail.setTo(array[0]);
            sendMail.setFrom("991386539@qq.com");
            sendMail.setSubject("注册验证码");
            sendMail.setContent(uuid);
            if (sendMail.send()) {
                System.out.println("---发送成功---");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.toJson("验证码发送失败");
        }
        return JsonUtil.toJson("验证码发送成功请注意查收");
    }

    /**
     * 添加用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/register.do")
    public String register(@RequestBody String array[], HttpSession session) {
        if (session.getAttribute("uuid") == null) {
            return JsonUtil.toJson("请先发送验证码再注册");
        }
        String uuid = String.valueOf(session.getAttribute("uuid"));
        User u = userMapper.getByName(array[0]);//判断该用户名是否已经注册过
        if (u == null) {
            return JsonUtil.toJson("该用户已注册请重新注册");
        } else if (!uuid.equals(array[4])) {
            return JsonUtil.toJson("验证码不匹配~");
        } else {
            User user = new User();
            user.setName(array[0]);
            user.setTel(array[1]);
            user.setPassword(array[2]);
            user.setPos(Integer.parseInt(array[3]));
            user.setCreateTime(System.currentTimeMillis());
            userMapper.insert(user);
            return JsonUtil.toJson("注册成功");//返回登陆页面
        }
    }


    /**
     * 退出登陆
     *
     * @param session
     * @return
     */
    @RequestMapping("/quit.do")
    public String back(HttpSession session) {
        session.removeAttribute("loginUser");
        return "login";
    }
}
