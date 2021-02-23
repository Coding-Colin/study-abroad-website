package com.study.manager.mapper;

import com.study.manager.bean.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseMapper {

    /**
     * 查询全部
     *
     * @return
     */
    List<Course> getAll();

    /**
     * 新增
     *
     * @param course
     */
    void add(Course course);


    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    Course getById(Integer id);


    /**
     * 更新
     *
     * @param course
     */
    void update(Course course);


    /**
     * 删除
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 更新图片
     *
     * @param id
     * @param url
     */
    void updateImgById(@Param("id") Integer id, @Param("url") String url);


    List<Course> getByTeacher(Integer id);


}
