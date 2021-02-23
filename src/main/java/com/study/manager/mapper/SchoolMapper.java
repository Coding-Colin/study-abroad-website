package com.study.manager.mapper;

import com.study.manager.bean.School;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchoolMapper {

    /**
     * 查询全部
     *
     * @return
     */
    List<School> getAll();

    /**
     * 新增
     *
     * @param school
     */
    void add(School school);


    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    School getById(Integer id);


    /**
     * 更新
     *
     * @param school
     */
    void update(School school);


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

    /**
     * 按照名字查询
     *
     * @return
     */
    List<School> getBySelect(String name);

}
