package com.study.manager.mapper;

import com.study.manager.bean.Information;

import java.util.List;

public interface InformationMapper {


    /**
     * 查询全部
     * @return
     */
    public List<Information> getAll();

    /**
     * 查询最新6个
     * @return
     */
    public List<Information> getDate6();


    /**
     * 新增
     * @param information
     */
    public void add(Information information);


    /**
     * 根据id查找
     * @param id
     * @return
     */
    public Information getById(Integer id);


    /**
     * 更新
     * @param information
     */
    public void update(Information information);


    /**
     * 删除
     * @param id
     */
    public void deleteById(Integer id);
}
