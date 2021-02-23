package com.study.manager.controller;

import com.study.manager.bean.*;
import com.study.manager.mapper.*;
import com.study.manager.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {

    @Resource
    public UserMapper userMapper;

    @Resource
    public CourseMapper courseMapper;

    @Resource
    public HouseMapper houseMapper;

    @Resource
    public InformationMapper informationMapper;

    @Resource
    public TeacherMapper teacherMapper;

    @Resource
    public TradeMapper tradeMapper;

    @Resource
    public SchoolMapper schoolMapper;

    /**
     * 客户端首页
     *
     * @return
     */
    @RequestMapping("/indexHtml.do")
    public String indexHtml(Model model) {
        List<Teacher> list = teacherMapper.getAll();
        model.addAttribute("logs", list);
        List<Information> list1 = informationMapper.getDate6();
        model.addAttribute("infs", list1);
        return "customer/index";
    }


    /**
     * 新闻公告
     *
     * @return
     */
    @RequestMapping("/consHtml.do")
    public String consHtml(Model model) {
        List<Information> list = informationMapper.getAll();
        model.addAttribute("logs", list);
        return "customer/notice";
    }

    /**
     * 教师列表
     *
     * @return
     */
    @RequestMapping("/cteacherHtml.do")
    public String cteacherHtml(Model model) {
        List<Teacher> list = teacherMapper.getAll();
        model.addAttribute("logs", list);
        return "customer/teacher";
    }

    /**
     * 热门学校
     *
     * @return
     */
    @RequestMapping("/popularSchoolHtml.do")
    public String popularSchoolHtml(Model model) {
        List<School> list = schoolMapper.getAll();
        model.addAttribute("logs", list);
        return "customer/popular";
    }

    /**
     * 热门学校
     *
     * @return
     */
    @RequestMapping("/selectSchool/{name}.do")
    public String selectSchoole(@PathVariable("name") String name, Model model) {
        List<School> list = schoolMapper.getBySelect(name);
        model.addAttribute("logs", list);
        return "customer/popular";
    }


    /**
     * 学校附近房源
     *
     * @return
     */
    @RequestMapping("/nearHouseHtml.do")
    public String nearHouseHtml(Model model) {
//        School school = schoolMapper.getById(id);
//        String address = school.saddress.split("-")[0];
//        List<House> list = houseMapper.getLikeAdd(address);
        List<House> list = houseMapper.getAll();
        model.addAttribute("logs", list);
        return "customer/house";
    }


    /**
     * 酒店预定
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("houseOrderHtml.do")
    public String houseOrderHtml(@RequestBody String array[], HttpSession session) {
        int id = Integer.parseInt(array[0]);
        House house = houseMapper.getById(id);
        session.setAttribute("id", id);
        session.setAttribute("price", house.hprice);
        return JsonUtil.toJson("success");
    }


    /**
     * 酒店预定页面
     *
     * @return
     */

    @RequestMapping("payhouseOrderHtml.do")
    public String payhouseOrderHtml() {
        return "pay/houseOrder";
    }


    /**
     * 支付酒店
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/payHouse.do")
    public String payHouse(HttpSession session) {
        String loginUser = String.valueOf(session.getAttribute("loginUser"));
        Integer id = Integer.parseInt(String.valueOf(session.getAttribute("id")));
        House house = houseMapper.getById(id);
        Trade trade = new Trade();
        trade.setCnum(1);
        trade.setCname(house.hname);
        trade.setCprice(house.hprice);
        trade.setCpic(house.hpic);
        trade.setLoginUser(loginUser);
        trade.setType("jd");
        tradeMapper.orderHouse(trade);
        return JsonUtil.toJson("预定成功");
    }


    /**
     * 课程列表
     *
     * @return
     */
    @RequestMapping("/courseShopHtml.do")
    public String courseShopHtml(Model model) {
        List<Course> list = courseMapper.getAll();
        model.addAttribute("logs", list);
        return "customer/course";
    }

    /**
     * 课程列表
     *
     * @return
     */
    @RequestMapping("/courseByTeacher/{id}.do")
    public String courseShopHtml(@PathVariable("id") Integer id, Model model) {
        List<Course> list = courseMapper.getByTeacher(id);
        model.addAttribute("logs",list);
        return "customer/course";
    }

    /**
     * 跳转购物车
     *
     * @return
     */
    @RequestMapping("/shoppingcarHtml.do")
    public String shoppingcarHtml(Model model, HttpSession session) {
        String loginUser = String.valueOf(session.getAttribute("loginUser"));
        List<Trade> list = tradeMapper.getByLoginUserP0(loginUser);
        model.addAttribute("logs", list);
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            num += list.get(i).cprice;
        }
        model.addAttribute("price", num);
        session.setAttribute("price", num);
        return "customer/shoppingcar";
    }


    /**
     * 加入购物车
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("addshoppingcar.do")
    public String addShoppingcar(@RequestBody Integer id, HttpSession session) {
        String loginUser = String.valueOf(session.getAttribute("loginUser"));
        Course course = courseMapper.getById(id);
        Trade trade = new Trade();
        trade.setCname(course.cname);
        trade.setLoginUser(loginUser);
        trade.setCnum(1);
        trade.setCpic(course.cpic);
        trade.setCprice(course.cprice);
        trade.setType("kc");
        tradeMapper.add(trade);
        return JsonUtil.toJson("加入成功");
    }


    /**
     * 移除购物车
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteshoppingcar.do")
    public String deleteshoppingcar(@RequestBody Integer id) {
        tradeMapper.deleteById(id);
        return JsonUtil.toJson("移除成功");
    }


    /**
     * 支付页面
     *
     * @return
     */
    @RequestMapping("payHtml.do")
    public String payHtml() {
        return "pay/courOrder";
    }

    /**
     * 支付课程
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/pay.do")
    public String pay(HttpSession session) {
        String loginUser = String.valueOf(session.getAttribute("loginUser"));
        List<Trade> list = tradeMapper.getByLoginUserP0(loginUser);
        for (int i = 0; i < list.size(); i++) {
            tradeMapper.updateById(list.get(i).id);
        }
        return JsonUtil.toJson("购买成功");
    }

    /**
     * 我买到的
     *
     * @return
     */
    @RequestMapping("/mybuy.do")
    public String mybuy(Model model, HttpSession session) {
        String loginUser = String.valueOf(session.getAttribute("loginUser"));
        List<Trade> list = tradeMapper.getByLoginUserP1(loginUser);
        List<Trade> fw = new ArrayList<Trade>();
        List<Trade> kc = new ArrayList<Trade>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).type.equals("kc")) {
                kc.add(list.get(i));
            } else {
                fw.add(list.get(i));
            }
        }
        model.addAttribute("kc", kc);
        model.addAttribute("fw", fw);
        return "customer/mybuy";
    }

    /**
     * 跳转个人设置
     *
     * @return
     */
    @RequestMapping("/personalHtml.do")
    public String personalHtml() {
        return "customer/personal";
    }

    /**
     * 查询个人信息
     */
    @ResponseBody
    @RequestMapping("/selectLoginUser.do")
    public String selectUser(Model model, HttpSession session) {
        String loginUser = String.valueOf(session.getAttribute("loginUser"));
        User user = userMapper.getByName(loginUser);
        return JsonUtil.toJson(user);
    }

    /**
     * 修改个人信息
     */
    @ResponseBody
    @RequestMapping("/cupdateUser.do")
    public String uodateUser(@RequestBody String[] array, HttpSession session) {
        String loginUser = String.valueOf(session.getAttribute("loginUser"));
        User user = userMapper.getByName(loginUser);
        user.setTel(array[0]);
        user.setPassword(array[1]);
        userMapper.update(user);
        return JsonUtil.toJson("修改成功");
    }

}
