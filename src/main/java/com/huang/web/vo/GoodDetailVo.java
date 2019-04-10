package com.huang.web.vo;

import com.huang.web.domain.SSUser;
import lombok.Data;

/**
 * 往商品详情页面传值的对象
 * @Author huangzt
 * @Date 2019.04.10
 * @Version 1.0
 */
@Data
public class GoodDetailVo {

    private SSUser user;
    private GoodsVo goodsVo;
    private int s_static = 0;
    private int remainSeconds = 0;
}
