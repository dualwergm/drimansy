package com.dg.drimansy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;

@Data
@Entity
@Table(name = "dailyoutdetail")
public class DailyOutDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "code_dod", nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "product_dod")
	private Product product;
	
	@Column(name = "amountout_dod", nullable = false)
	private Long amountOut;
	
	@Column(name = "amountin_dod", nullable = false)
	private Long amountIn;
	
	@Column(name = "price_dod", nullable = false)
	private Double price;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dailyout_dod")
	private DailyOut dailyOut;
	
	public void toEntity(JsonNode json) {
		long lId = json.get("id").asLong();
		setId(lId > 0 ? Long.valueOf(lId) : null);
		setProduct(new Product(json.get("productId").asLong()));
		setAmountOut(json.get("amountOut").asLong());
		setAmountIn(json.get("amountIn").asLong());
		setPrice(json.get("price").asDouble());
	}
}
