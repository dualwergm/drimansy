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
@Table(name = "category")
public class Category implements Serializable{
	private static final long serialVersionUID = -8292772128517864441L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "code_ctg")
	private Long id;
	
	@Column(name = "name_ctg")
	private String name;
}
