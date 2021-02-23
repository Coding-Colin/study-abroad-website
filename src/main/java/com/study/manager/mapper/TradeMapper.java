package com.study.manager.mapper;

import com.study.manager.bean.Trade;

import java.util.List;

public interface TradeMapper {

    /**
     * 根据登陆用户查询购物车
     *
     * @return
     */
    List<Trade> getByLoginUserP0(String loginUser);


    /**
     * 根据登陆用户查询买到
     *
     * @return
     */
    List<Trade> getByLoginUserP1(String loginUser);

    /**
     * 新增
     *
     * @return
     */
    void add(Trade trade);

    /**
     * 更新购物车状态
     *
     * @return
     */
    void updateById(Integer id);

    /**
     * 查询已卖出根据类型
     *
     * @return
     */
    List<Trade> getAllP1(String type);


    /**
     * 根据id删除
     *
     * @return
     */
    void deleteById(Integer id);

    /**
     * 订酒店
     *
     * @return
     */
    void orderHouse(Trade trade);


}
