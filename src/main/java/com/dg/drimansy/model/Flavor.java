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
@Table(name = "flavor")
public class Flavor implements Serializable{
	private static final long serialVersionUID = 453051074046204092L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "code_fvr")
	private Long id;
	
	@Column(name = "name_fvr")
	private String name;
}
