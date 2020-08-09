package com.dg.drimansy.view.vo;

import java.io.Serializable;

import com.dg.drimansy.model.Collaborator;

import lombok.Data;

@Data
public class CollaboratorVO implements Serializable{
	private static final long serialVersionUID = -6956865605294146833L;
	private Long id;
	private String name;
	private String identification;
	private String email;
	
	public void toVO(Collaborator collaborator) {
		setId(collaborator.getId());
		setName(collaborator.getName());
		setIdentification(collaborator.getIdentification());
		setEmail(collaborator.getEmail());
	}
}
