package com.dg.drimansy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "invoicedetail")
public class InvoiceDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "code_idt", nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "product_idt")
	private Product product;
	
	@Column(name = "amount_idt", nullable = false)
	private Long amount;
	
	@Column(name = "price_idt", nullable = false)
	private Double price;	
	
	@ManyToOne
	@JoinColumn(name = "invoice_idt")
	private Invoice invoice;
	
	public void toEntity(JsonNode json) {
		long lId = json.get("id").asLong();
		setId(lId > 0 ? Long.valueOf(lId) : null);
		setProduct(new Product(json.get("productId").asLong()));
		setAmount(json.get("amount").asLong(0));
		setPrice(json.get("price").asDouble(0));
	}
}
