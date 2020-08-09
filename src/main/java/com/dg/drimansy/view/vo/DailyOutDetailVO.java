package com.dg.drimansy.view.vo;

import com.dg.drimansy.model.DailyOutDetail;

import lombok.Data;

@Data
public class DailyOutDetailVO {
	private Long id;
	private ProductVO product;
	private Long amountOut;
	private Long amountIn;
	private Double price;
	
	public void toVO(DailyOutDetail dailyOutDetail) {
		setId(dailyOutDetail.getId());
		ProductVO productVO = new ProductVO();
		productVO.toVO(dailyOutDetail.getProduct());
		setProduct(productVO);
		setAmountOut(dailyOutDetail.getAmountOut());
		setAmountIn(dailyOutDetail.getAmountIn());
		setPrice(dailyOutDetail.getPrice());
	}
}
