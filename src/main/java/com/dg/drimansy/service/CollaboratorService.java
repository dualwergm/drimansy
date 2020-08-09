package com.dg.drimansy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dg.drimansy.model.Collaborator;
import com.dg.drimansy.repository.ICollaboratorRepository;
import com.dg.drimansy.view.vo.CollaboratorVO;

@Service
public class CollaboratorService {
	private final ICollaboratorRepository collaboratorRepository;
	
	public CollaboratorService(ICollaboratorRepository collaboratorRepository) {
		this.collaboratorRepository = collaboratorRepository;
	}
	
	public List<CollaboratorVO> getCollaboratorsVO() {
		List<Collaborator> collaborators = this.collaboratorRepository.findAllOrdered();
		return getCollaboratorsVO(collaborators);
	}
	
	@Transactional(readOnly = true)
	public List<Collaborator> findAllOrdered() {
		return this.collaboratorRepository.findAllOrdered();
	}
	
	public List<CollaboratorVO> getCollaboratorsVO(List<Collaborator> collaborators){
		List<CollaboratorVO> collaboratorsVO = new ArrayList<CollaboratorVO>();
		for(Collaborator collaborator: collaborators) {
			CollaboratorVO cVO = new CollaboratorVO();
			cVO.toVO(collaborator);
			collaboratorsVO.add(cVO);
		}
		return collaboratorsVO;
	}
}
