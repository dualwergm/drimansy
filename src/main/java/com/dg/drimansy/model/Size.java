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
@Table(name="size")
public class Size implements Serializable{
	private static final long serialVersionUID = -5931793608573376610L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "code_siz")
	private Long id;
	
	@Column(name = "name_siz")
	private String name;
}
