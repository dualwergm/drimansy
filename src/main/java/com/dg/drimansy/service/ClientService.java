package com.dg.drimansy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dg.drimansy.model.Client;
import com.dg.drimansy.repository.IClientRepository;
import com.dg.drimansy.view.vo.ClientVO;

@Service
public class ClientService {
	private final IClientRepository clientRepository;
	
	public ClientService(IClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	public List<ClientVO> getClientsVO() {
		List<Client> clients = this.clientRepository.findAllOrdered();
		return getClientsVO(clients);
	}
	
	@Transactional(readOnly = true)
	public List<Client> findAllOrdered() {
		return this.clientRepository.findAllOrdered();
	}
	
	public List<ClientVO> getClientsVO(List<Client> clients){
		List<ClientVO> clientsVO = new ArrayList<ClientVO>();
		for(Client client: clients) {
			ClientVO cVO = new ClientVO();
			cVO.toVO(client);
			clientsVO.add(cVO);
		}
		return clientsVO;
	}
}
