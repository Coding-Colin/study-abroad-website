package com.study.manager.controller;

import com.study.manager.bean.*;
import com.study.manager.mapper.*;
import com.study.manager.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {

    @Resource
    public UserMapper userMapper;

    @Resource
    public CourseMapper courseMapper;

    @Resource
    public HouseMapper houseMapper;

    @Resource
    public SchoolMapper schoolMapper;

    @Resource
    public InformationMapper informationMapper;

    @Resource
    public TeacherMapper teacherMapper;

    @Resource
    public TradeMapper tradeMapper;

    public static String PATH = "";//项目路径source/img的绝对路径


    /**
     * 跳转用户管理
     *
     * @return
     */
    @RequestMapping("/userHtml.do")
    public String userHtml(Model model) {
        List<User> list = userMapper.getAll();
        model.addAttribute("logs", list);
        return "admin/userList";
    }

    /**
     * 查询用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectuser.do")
    public String getUser(@RequestBody Integer id) {
        User user = userMapper.getById(id);
        return JsonUtil.toJson(user);
    }


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteuser.do")
    public String deleteUser(@RequestBody Integer id) {
        userMapper.deleteById(id);
        return JsonUtil.toJson("删除成功");
    }


    /**
     * 跳转课程管理
     *
     * @return
     */
    @RequestMapping("/courseHtml.do")
    public String courseHtml(Model model) {
        List<Course> list = courseMapper.getAll();
        model.addAttribute("logs", list);
        return "admin/courseList";
    }

    /**
     * 添加课程
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/addCourse.do")
    public String addUser(@RequestBody String array[]) throws Exception {
        Course course = new Course();
        course.setCname(array[0]);
        course.setCintroduce(array[1]);
        course.setCdate((new SimpleDateFormat("yyyy-MM-dd")).parse(array[2]));
        course.setCprice(Integer.parseInt(array[3]));
        courseMapper.add(course);
        return JsonUtil.toJson("添加成功");
    }

    /**
     * 查询课程
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectcourse.do")
    public String getCourse(@RequestBody Integer id) {
        Course course = courseMapper.getById(id);
        return JsonUtil.toJson(course);
    }

    /**
     * 更新课程图片
     *
     * @param file
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/upload1/{id}.do")
    public String updateImg1(@RequestParam("file") MultipartFile file,
                             @PathVariable("id") Integer id) {
        //设置上传的图片
        String fileName = file.getOriginalFilename();
        String filePath = PATH + "course\\";//保存的真实路径
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);//保存文件
            courseMapper.updateImgById(id, "img/course/" + fileName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();//异常信息的输出
        }
        return JsonUtil.toJson(courseMapper.getById(id));
    }

    /**
     * 更新课程
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateCourse.do")
    public String updateCourse(@RequestBody String array[]) throws Exception {
        Course course = courseMapper.getById(Integer.parseInt(array[0]));
        course.setCname(array[1]);
        course.setCintroduce(array[2]);
        course.setCdate((new SimpleDateFormat("yyyy-MM-dd")).parse(array[3]));
        course.setCprice(Integer.parseInt(array[4]));
        courseMapper.update(course);
        return JsonUtil.toJson("更新成功");//返回登陆页面
    }

    /**
     * 删除课程
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deletecourse.do")
    public String deletecourse(@RequestBody Integer id) {
        courseMapper.deleteById(id);
        return JsonUtil.toJson("删除成功");
    }

    /**
     * 跳转教师管理
     *
     * @return
     */
    @RequestMapping("/teacherHtml.do")
    public String teacherHtml(Model model) {
        List<Teacher> list = teacherMapper.getAll();
        model.addAttribute("logs", list);
        return "admin/teacherList";
    }

    /**
     * 添加老师
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/addTeacher.do")
    public String addTeacher(@RequestBody String array[]) throws Exception {
        Teacher teacher = new Teacher();
        teacher.setTname(array[0]);
        teacher.setTsubject(array[1]);
        teacher.setThonor(array[2]);
        teacherMapper.add(teacher);
        return JsonUtil.toJson("添加成功");
    }

    /**
     * 查询老师
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectteacher.do")
    public String getTeacher(@RequestBody Integer id) {
        Teacher teacher = teacherMapper.getById(id);
        return JsonUtil.toJson(teacher);
    }


    /**
     * 更新老师图片
     *
     * @param file
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/upload4/{id}.do")
    public String updateImg4(@RequestParam("file") MultipartFile file,
                             @PathVariable("id") Integer id) {
        //设置上传的图片
        String fileName = file.getOriginalFilename();
        String filePath = PATH + "teacher\\";//保存的真实路径
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);//保存文件
            teacherMapper.updateImgById(id, "img/teacher/" + fileName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();//异常信息的输出
        }
        return JsonUtil.toJson(teacherMapper.getById(id));
    }


    /**
     * 更新老师
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateTeacher.do")
    public String updateTeacher(@RequestBody String array[]) {
        Teacher teacher = teacherMapper.getById(Integer.parseInt(array[0]));
        teacher.setTname(array[1]);
        teacher.setTsubject(array[2]);
        teacher.setThonor(array[3]);
        teacherMapper.update(teacher);
        return JsonUtil.toJson("更新成功");//返回登陆页面
    }

    /**
     * 删除教师
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteteacher.do")
    public String deleteteacher(@RequestBody Integer id) {
        teacherMapper.deleteById(id);
        return JsonUtil.toJson("删除成功");
    }

    /**
     * 跳转学校管理
     *
     * @return
     */
    @RequestMapping("/schoolHtml.do")
    public String schoolHtml(Model model) {
        List<School> list = schoolMapper.getAll();
        model.addAttribute("logs", list);
        return "admin/schoolList";
    }

    /**
     * 添加学校
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/addSchool.do")
    public String addSchool(@RequestBody String array[]) {
        School school = new School();
        school.setSname(array[0]);
        school.setSaddress(array[1]);
        school.setSmoney(Integer.parseInt(array[2]));
        schoolMapper.add(school);
        return JsonUtil.toJson("添加成功");
    }


    /**
     * 查询学校
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectschool.do")
    public String getSchool(@RequestBody Integer id) {
        School school = schoolMapper.getById(id);
        return JsonUtil.toJson(school);
    }

    /**
     * 更新学校图片
     *
     * @param file
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/upload3/{id}.do")
    public String updateImg3(@RequestParam("file") MultipartFile file,
                             @PathVariable("id") Integer id) {
        //设置上传的图片
        String fileName = file.getOriginalFilename();
        String filePath = PATH + "school\\";//保存的真实路径
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);//保存文件
            schoolMapper.updateImgById(id, "img/school/" + fileName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();//异常信息的输出
        }
        return JsonUtil.toJson(schoolMapper.getById(id));
    }

    /**
     * 更新学校
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateSchool.do")
    public String updateSchool(@RequestBody String array[]) {
        School school = schoolMapper.getById(Integer.parseInt(array[0]));
        school.setSname(array[1]);
        school.setSaddress(array[2]);
        school.setSmoney(Integer.parseInt(array[3]));
        schoolMapper.update(school);
        return JsonUtil.toJson("更新成功");//返回登陆页面
    }

    /**
     * 删除学校
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteschool.do")
    public String deleteschool(@RequestBody Integer id) {
        schoolMapper.deleteById(id);
        return JsonUtil.toJson("删除成功");
    }


    /**
     * 跳转房屋管理
     *
     * @return
     */
    @RequestMapping("/houseHtml.do")
    public String houseHtml(Model model) {
        List<House> list = houseMapper.getAll();
        model.addAttribute("logs", list);
        return "admin/houseList";
    }

    /**
     * 添加房源
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/addHouse.do")
    public String addHouse(@RequestBody String array[]) throws Exception {
        House house = new House();
        house.setHname(array[0]);
        house.setHaddress(array[1]);
        house.setHprice(Integer.parseInt(array[2]));
        house.setHtel(array[3]);
        houseMapper.add(house);
        return JsonUtil.toJson("添加成功");
    }

    /**
     * 查询房源
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/selecthouse.do")
    public String getHouse(@RequestBody Integer id) {
        House house = houseMapper.getById(id);
        return JsonUtil.toJson(house);
    }

    /**
     * 更新房源图片
     *
     * @param file
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/upload2/{id}.do")
    public String updateImg2(@RequestParam("file") MultipartFile file,
                             @PathVariable("id") Integer id) {
        //设置上传的图片
        String fileName = file.getOriginalFilename();
        String filePath = PATH + "house\\";//保存的真实路径
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);//保存文件
            houseMapper.updateImgById(id, "img/house/" + fileName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();//异常信息的输出
        }
        return JsonUtil.toJson(houseMapper.getById(id));
    }

    /**
     * 更新房源
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateHouse.do")
    public String updateHouse(@RequestBody String array[]) {
        House house = houseMapper.getById(Integer.parseInt(array[0]));
        house.setHname(array[1]);
        house.setHaddress(array[2]);
        house.setHprice(Integer.parseInt(array[3]));
        house.setHtel(array[4]);
        houseMapper.update(house);
        return JsonUtil.toJson("更新成功");//返回登陆页面
    }


    /**
     * 删除房源
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deletehouse.do")
    public String deletehouse(@RequestBody Integer id) {
        houseMapper.deleteById(id);
        return JsonUtil.toJson("删除成功");
    }


    /**
     * 跳转公告页面
     *
     * @return
     */
    @RequestMapping("/informationHtml.do")
    public String informationHtml(Model model) {
        List<Information> list = informationMapper.getAll();
        model.addAttribute("logs", list);
        return "admin/infomationList";
    }

    /**
     * 添加公告
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/addInformation.do")
    public String addInformation(@RequestBody String array[]) {
        Information information = new Information();
        information.setTitle(array[0]);
        information.setContent(array[1]);
        informationMapper.add(information);
        return JsonUtil.toJson("添加成功");
    }


    /**
     * 查询公告
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectinformation.do")
    public String getInformation(@RequestBody Integer id) {
        Information information = informationMapper.getById(id);
        return JsonUtil.toJson(information);
    }

    /**
     * 更新公告
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateInformation.do")
    public String updateInformation(@RequestBody String array[]) {
        Information information = informationMapper.getById(Integer.parseInt(array[0]));
        information.setTitle(array[1]);
        information.setContent(array[2]);
        informationMapper.update(information);
        return JsonUtil.toJson("更新成功");//返回登陆页面
    }

    /**
     * 删除公告
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteinformation.do")
    public String deleteinformation(@RequestBody Integer id) {
        informationMapper.deleteById(id);
        return JsonUtil.toJson("删除成功");
    }

    /**
     * 跳转订单管理
     *
     * @return
     */
    @RequestMapping("/tradeHtml.do")
    public String tradeHtml(Model model) {
        List<Trade> list = tradeMapper.getAllP1("jd");
        List<Trade> list1 = tradeMapper.getAllP1("kc");
        model.addAttribute("jds", list);
        model.addAttribute("kcs", list1);
        return "admin/tradeList";
    }


}
