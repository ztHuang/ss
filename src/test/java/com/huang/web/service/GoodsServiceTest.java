package com.huang.web.service;

import com.huang.web.vo.GoodsVo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTest {

    @Autowired
    GoodsService goodsService;

    @org.junit.Test
    public void listGoodsVo() {
        List<GoodsVo> goodsVos = goodsService.listGoodsVo();
        goodsVos.stream().forEach(System.out::print);
    }
}
