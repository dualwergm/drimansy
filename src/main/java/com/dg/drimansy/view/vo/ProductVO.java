package com.dg.drimansy.view.vo;

import com.dg.drimansy.model.Product;

import lombok.Data;

@Data
public class ProductVO {
	private Long id;
	private String name;
	private String sequence;
	private Double price;
	private SizeVO sizeVO;
	private FlavorVO flavorVO;
	private CategoryVO categoryVO;
	private Integer active;
	
	public void toVO(Product product) {
		setId(product.getId());
		setName(product.getName());
		setSequence(product.getSequence());
		setPrice(product.getPrice());
		SizeVO sizeVO = new SizeVO();
		sizeVO.toVO(product.getSize());
		setSizeVO(sizeVO);
		FlavorVO flavorVO = new FlavorVO();
		flavorVO.toVO(product.getFlavor());
		setFlavorVO(flavorVO);
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.toVO(product.getCategory());
		setCategoryVO(categoryVO);
		setActive(product.getActive());
	}
}
