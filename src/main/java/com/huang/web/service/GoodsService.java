package com.huang.web.service;

import com.huang.web.dao.GoodsDao;
import com.huang.web.domain.Goods;
import com.huang.web.domain.SSGoods;
import com.huang.web.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.04.01
 * @Version 1.0
 */
@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsDao.getGoodsVoByGoodsId(goodsId);
    }

    /**
     * 将传入的商品的库存数减一
     * @param goods
     */
    public void reduceStock(GoodsVo goods) {
        SSGoods ssGood = new SSGoods();
        ssGood.setGoodsId(goods.getId());
        goodsDao.reduceStock(ssGood);
    }
}
