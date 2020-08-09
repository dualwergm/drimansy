package com.dg.drimansy.view.vo;

import com.dg.drimansy.model.Category;

import lombok.Data;

@Data
public class CategoryVO {
	private Long id;
	private String name;
	
	public void toVO(Category category) {
		setId(category.getId());
		setName(category.getName());
	}
}
