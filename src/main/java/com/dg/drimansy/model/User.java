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
@Table(name = "users")
public class User implements Serializable{
	private static final long serialVersionUID = -9032036564253523086L;

	public User() {}
	
	public User(long id) {
		this.id = id > 0 ? Long.valueOf(id) : null;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "code_usr", nullable = false)
	private Long id;
	
	@Column(name = "name_usr", nullable = false)
	private String name;
	
	@Column(name = "user_usr", nullable = false)
	private String user;
	
	@Column(name = "password_usr", nullable = false)
	private String password;
	
	@Column(name = "active_usr", nullable = false)
	private Integer active;
}
