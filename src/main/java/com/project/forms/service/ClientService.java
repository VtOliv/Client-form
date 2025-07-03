package com.project.forms.service;

import org.springframework.stereotype.Service;

import com.project.forms.domain.Client;
import com.project.forms.domain.DTO.ClientDTO;
import com.project.forms.domain.Enum.ClientType;
import com.project.forms.exception.CNPJNotFoundException;
import com.project.forms.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

	
	private final ClientRepository clientRepository;
	private final AddressService addressService;
	
	public Client newClient(ClientDTO dto) {

		if(dto.getClientType() == ClientType.PJ) {
			validatePJ(dto);
		}
		
		var newClient = Client.builder()
				.name(dto.getName())
				.email(dto.getEmail())
				.clientCPF(dto.getClientCPF())
				.clientCNPJ(dto.getClientCNPJ())
				.clientType(dto.getClientType())
				.mobileTelephone(dto.getMobileTelephone())
				.fixedTelephone(dto.getFixedTelephone())
				.address(addressService.buildAddress(dto.getAddress()))
				.build();
		
		var savedClient = clientRepository.save(newClient);
		
		return savedClient;
	}
	
	public void validatePJ(ClientDTO dto) {

			if(dto.getClientCNPJ() == null) {
				throw new CNPJNotFoundException();
			}
	}
	
}
