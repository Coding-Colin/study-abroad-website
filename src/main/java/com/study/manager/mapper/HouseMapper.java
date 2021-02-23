package com.study.manager.mapper;

import com.study.manager.bean.House;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {


    /**
     * 查询全部
     *
     * @return
     */
    List<House> getAll();

    /**
     * 新增
     *
     * @param house
     */
    void add(House house);


    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    House getById(Integer id);


    /**
     * 更新
     *
     * @param house
     */
    void update(House house);


    /**
     * 删除
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 按照地区模糊查询
     *
     * @param add
     * @return
     */
    List<House> getLikeAdd(String add);

    /**
     * 更新图片
     *
     * @param id
     * @param url
     */
    void updateImgById(@Param("id") Integer id, @Param("url") String url);
}
