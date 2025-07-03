package com.project.forms.service;

import static com.project.forms.factory.ClientFactory.mockClientDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.project.forms.domain.AddressValidationResponse;
import com.project.forms.domain.Client;
import com.project.forms.domain.DTO.ClientDTO;
import com.project.forms.domain.Enum.ClientType;
import com.project.forms.exception.CNPJNotFoundException;
import com.project.forms.repository.ClientRepository;

public class ClientServiceTest {

	@Mock
	private ClientRepository clientRepository;

	@Mock
	private RestTemplate restTemplate;

	private AddressService addressService;

	private ClientService clientService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		AddressValidationResponse response = AddressValidationResponse.builder().erro(null).build();

		when(restTemplate.getForObject(anyString(), eq(AddressValidationResponse.class))).thenReturn(response);

		addressService = new AddressService(restTemplate);
		clientService = new ClientService(clientRepository, addressService);
	}

	@Test
	void shouldCreateNewClientWithTypePF() {
		var dto = mockClientDTO(ClientType.PF);

		when(clientRepository.save(any())).thenAnswer(invocation -> {
			Client c = invocation.getArgument(0);
			c.setClientId(1L);
			return c;
		});

		var saved = clientService.newClient(dto);

		assertNotNull(saved);
		assertEquals("João", saved.getName());
		assertEquals("01310-200", saved.getAddress().getZipCode());
		verify(clientRepository, times(1)).save(any());
	}

	@Test
	void shouldCreateNewClientWithTypePJAndCNPJ() {
		var dto = mockClientDTO(ClientType.PJ);

		when(clientRepository.save(any())).thenAnswer(invocation -> {
			Client c = invocation.getArgument(0);
			c.setClientId(2L);
			return c;
		});

		var saved = clientService.newClient(dto);

		assertNotNull(saved);
		assertEquals("Empresa X", saved.getName());
		assertEquals("01310-200", saved.getAddress().getZipCode());
		verify(clientRepository, times(1)).save(any());
	}

	@Test
	void shouldThrowExceptionWhenCNPJIsNullForPJ() {
		var dto = new ClientDTO();
		dto.setClientType(ClientType.PJ);
		dto.setClientCNPJ(null);

		assertThrows(CNPJNotFoundException.class, () -> clientService.newClient(dto));
		verify(clientRepository, never()).save(any());
	}
}
