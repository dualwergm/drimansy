package com.dg.drimansy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="car")
public class Car implements Serializable{
	private static final long serialVersionUID = -8153426084116473337L;

	public Car() {
		//
	}
	
	public Car(long id) {
		this.id = id > 0 ? Long.valueOf(id) : null;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "code_car")
	private Long id;
	
	@Column(name = "plate_car")
	private String plate;
}
