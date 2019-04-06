package com.huang.web.vo;

import com.huang.web.domain.Goods;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author huangzt
 * @Date 2019.04.01
 * @Version 1.0
 */
@Data
public class GoodsVo extends Goods {
    private Double ssPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
