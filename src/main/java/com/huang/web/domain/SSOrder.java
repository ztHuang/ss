package com.huang.web.domain;

import lombok.Data;

/**
 * 秒杀订单
 */
@Data
public class SSOrder {
	private Long id;
	private Long userId;
	private Long  orderId;
	private Long goodsId;
}
