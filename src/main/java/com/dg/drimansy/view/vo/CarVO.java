package com.dg.drimansy.view.vo;

import java.io.Serializable;

import com.dg.drimansy.model.Car;

import lombok.Data;

@Data
public class CarVO implements Serializable{
	private static final long serialVersionUID = 5024968680768035895L;
	private Long id;
	private String plate;
	
	public void toVO(Car car) {
		setId(car.getId());
		setPlate(car.getPlate());
	}
}
