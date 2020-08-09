package com.dg.drimansy.view.vo;

import java.io.Serializable;

import com.dg.drimansy.model.Client;

import lombok.Data;

@Data
public class ClientVO implements Serializable{
	private static final long serialVersionUID = 5024968680768035895L;
	private Long id;
	private String name;
	
	public void toVO(Client client) {
		setId(client.getId());
		setName(client.getName());
	}
}
