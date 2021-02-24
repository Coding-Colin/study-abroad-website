package com.study.manager.mapper;

import com.study.manager.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {


    /**
     * 查询全部
     *
     * @return
     */
    List<User> getAll();


    /**
     * 注册
     *
     * @param user
     */
    void insert(User user);


    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 根据name查询用户信息
     *
     * @param name
     * @return
     */
    User getByName(String name);


    /**
     * 根据用户名更新信息
     */
    void update(User user);


    /**
     * 删除用户
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 用户授权
     *
     * @param id
     */
    void upPos(Integer id);

    void updateLoginCount(@Param("username") String username,@Param("loginCount")  Long loginCount);
}
