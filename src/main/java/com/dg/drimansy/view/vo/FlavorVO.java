package com.dg.drimansy.view.vo;

import com.dg.drimansy.model.Flavor;

import lombok.Data;

@Data
public class FlavorVO {
	private Long id;
	private String name;
	
	public void toVO(Flavor flavor) {
		setId(flavor.getId());
		setName(flavor.getName());
	}
}
