package com.dg.drimansy.view.vo;

import com.dg.drimansy.model.InvoiceDetail;

import lombok.Data;

@Data
public class InvoiceDetailVO {
	private Long id;
	private ProductVO product;
	private Long amount;
	private Double price;
	
	public void toVO(InvoiceDetail invoiceDetail) {
		setId(invoiceDetail.getId());
		ProductVO productVO = new ProductVO();
		productVO.toVO(invoiceDetail.getProduct());
		setProduct(productVO);
		setAmount(invoiceDetail.getAmount());
		setPrice(invoiceDetail.getPrice());
	}
}
