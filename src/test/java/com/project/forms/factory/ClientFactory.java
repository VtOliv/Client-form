package com.project.forms.factory;

import com.project.forms.domain.Address;
import com.project.forms.domain.DTO.AddressDTO;
import com.project.forms.domain.DTO.ClientDTO;
import com.project.forms.domain.Enum.ClientType;

public class ClientFactory {

	public static Address mockAddress() {
		return Address.builder()
				.zipCode("01310-200")
				.streetName("Av. Paulista")
				.city("São Paulo")
				.district("Bela Vista")
				.state("SP")
				.build();
	}

	public static AddressDTO mockAddressDTO() {
		return AddressDTO.builder()
				.zipCode("01310-200")
				.streetName("Av. Paulista")
				.city("São Paulo")
				.district("Bela Vista")
				.state("SP")
				.build();
	}
	
	public static ClientDTO mockClientDTO(ClientType type) {
		
		var dto = ClientDTO.builder()
				.name("João")
				.email("joao@email.com")
				.clientCPF("12345678901")
				.mobileTelephone("11999999999")
				.fixedTelephone("1133334444")
				.address(mockAddressDTO())
				.build();
		
		if(type == ClientType.PJ) {
	        dto.setName("Empresa X");
	        dto.setEmail("contato@empresa.com");
	        dto.setClientCNPJ("12345678000199");
		}
		
		return dto;

	}
}
