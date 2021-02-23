package com.study.manager.mapper;

import com.study.manager.bean.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {


    /**
     * 查询全部
     *
     * @return
     */
    List<Teacher> getAll();

    /**
     * 新增
     *
     * @param teacher
     */
    void add(Teacher teacher);


    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    Teacher getById(Integer id);


    /**
     * 更新
     *
     * @param teacher
     */
    void update(Teacher teacher);


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
}
