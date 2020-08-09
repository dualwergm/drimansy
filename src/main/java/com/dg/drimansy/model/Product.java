package com.dg.drimansy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
	
	public Product() {		//
	}
	
	public Product(long id) {
		this.id = id > 0 ? Long.valueOf(id) : null;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "code_prd", nullable = false)
	private Long id;
	
	@Column(name = "name_prd", nullable = false)
	private String name;
	
	@Column(name = "sequence_prd", nullable = false)
	private String sequence;
	
	@Column(name = "price_prd", nullable = false)
	private Double price;
	
	@ManyToOne
	@JoinColumn(name = "size_prd", nullable = false)
	private Size size;
	
	@ManyToOne
	@JoinColumn(name = "flavor_prd", nullable = false)
	private Flavor flavor;
	
	@ManyToOne
	@JoinColumn(name = "category_prd", nullable = false)
	private Category category;
	
	@Column(name = "active_prd", nullable = false)
	private Integer active;
}
