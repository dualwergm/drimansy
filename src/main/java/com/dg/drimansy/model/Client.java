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
@Table(name="client")
public class Client implements Serializable{
	private static final long serialVersionUID = 249998385078672645L;

	public Client() {}
	public Client(long id) {
		this.id = id > 0 ? Long.valueOf(id) : null;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "code_cli")
	private Long id;
	
	@Column(name = "name_cli")
	private String name;
}
