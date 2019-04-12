package com.huang.web.vo;

import com.huang.web.domain.OrderInfo;
import lombok.Data;

/**
 * @Author huangzt
 * @Date 2019.04.12
 * @Version 1.0
 */

@Data
public class OrderDetailVo {
    private GoodsVo goodsVo;
    private OrderInfo orderInfo;
}
