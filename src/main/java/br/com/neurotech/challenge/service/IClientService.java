package br.com.neurotech.challenge.service;

import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.entity.NeurotechClient;

@Service
public interface IClientService {
	
	/**
	 * Salva um novo cliente
	 * 
	 * @return ID do cliente recém-salvo
	 */
	String save(NeurotechClient client);
	
	/**
	 * Recupera um cliente baseado no seu ID
	 */
	NeurotechClient get(String id);

}
