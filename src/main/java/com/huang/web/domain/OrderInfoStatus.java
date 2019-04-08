package com.huang.web.domain;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.04.07
 * @Version 1.0
 */

import lombok.Getter;

/**
 * 订单状态：0、新建未支付   1、已支付  2、已发货  3、已收货  4、已退款  5、已完成
 */
@Getter
public enum OrderInfoStatus{

    NEW_UNPAID(0, "新建未支付"),
    PAID(1, "已支付"),
    SHIPPED(2, "已发货"),
    RECEIVED(3, "已收货"),
    REFUNDED(4, "已退款"),
    COMPLETED(5, "已完成"),
    ;

    private Integer code;
    private String msg;

    OrderInfoStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
