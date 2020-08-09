package com.dg.drimansy.view.vo;

import com.dg.drimansy.model.Size;

import lombok.Data;

@Data
public class SizeVO {
	private Long id;
	private String name;
	
	public void toVO(Size size) {
		setId(size.getId());
		setName(size.getName());
	}
}
