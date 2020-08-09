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
@Table(name="collaborator")
public class Collaborator implements Serializable{
	private static final long serialVersionUID = 3710511461585732731L;

	public Collaborator() {
		//
	}
	
	public Collaborator(long id) {
		this.id = id>0?Long.valueOf(id):null;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "code_clb")
	private Long id;
	
	@Column(name = "name_clb")
	private String name;
	
	@Column(name = "identification_clb")
	private String identification;
	
	@Column(name = "email_clb")
	private String email;
}
