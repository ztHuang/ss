package com.huang.web.dao;

import com.huang.web.domain.SSGoods;
import com.huang.web.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.04.01
 * @Version 1.0
 */
@Mapper
public interface GoodsDao {

    /**
     * 这里会自动驼峰格式转换
     * @return
     */
    @Select("select g.*,sg.stock_count,sg.start_date,sg.end_date,sg.ss_price from ss_goods sg left join goods g on sg.id = g.id")
    List<GoodsVo> listGoodsVo();

    @Select("select g.*,sg.stock_count,sg.start_date,sg.end_date,sg.ss_price from ss_goods sg left join goods g on sg.id = g.id where g.id = #{goodsId}")
    GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

    /**
     * sql语句加入库存判断，（stock_count > 0），避免库存出现负数
     * @param ssGood
     */
    @Update("update ss_goods set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count > 0")
    void reduceStock(SSGoods ssGood);
}
