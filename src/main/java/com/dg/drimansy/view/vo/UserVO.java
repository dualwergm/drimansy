package com.dg.drimansy.view.vo;

import java.io.Serializable;

import com.dg.drimansy.model.User;

import lombok.Data;

@Data
public class UserVO implements Serializable{
	private static final long serialVersionUID = 1579386630149515094L;
	private Long id;
	private String name;
	private String user;
	private String password;
	private Integer active;
	
	public void toVO(User user) {
		if(user == null) {
			return;
		}
		setId(user.getId());
		setName(user.getName());
	}
}
