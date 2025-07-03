package com.project.forms.service;

import static com.project.forms.factory.ClientFactory.mockAddressDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.project.forms.domain.AddressValidationResponse;
import com.project.forms.domain.DTO.AddressDTO;
import com.project.forms.exception.AddressNotFoundException;

class AddressServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private AddressService addressService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldReturnTrueWhenCepExists() {
		var response = AddressValidationResponse.builder().erro(null).build();

		when(restTemplate.getForObject(anyString(), eq(AddressValidationResponse.class))).thenReturn(response);

		boolean valid = addressService.isValidCep("01310-200");

		assertTrue(valid);
	}

	@Test
	void shouldReturnFalseWhenCepDoesNotExist() {
		var response = AddressValidationResponse.builder().erro(true).build();

		when(restTemplate.getForObject(anyString(), eq(AddressValidationResponse.class))).thenReturn(response);

		boolean valid = addressService.isValidCep("00000-000");

		assertFalse(valid);
	}

	@Test
	void shouldReturnFalseOnException() {
		when(restTemplate.getForObject(anyString(), eq(AddressValidationResponse.class)))
				.thenThrow(new RuntimeException("erro"));

		var valid = addressService.isValidCep("99999-999");

		assertFalse(valid);
	}

	@Test
	void shouldBuildAddressWhenCepIsValid() {
		var dto = mockAddressDTO();

		when(restTemplate.getForObject(anyString(), eq(AddressValidationResponse.class)))
				.thenReturn(AddressValidationResponse.builder().erro(null).build());

		var result = addressService.buildAddress(dto);

		assertNotNull(result);
		assertEquals("01310-200", result.getZipCode());
	}

	@Test
	void shouldThrowAddressNotFoundExceptionWhenCepIsInvalid() {
		var dto = new AddressDTO();
		dto.setZipCode("00000-000");

		when(restTemplate.getForObject(anyString(), eq(AddressValidationResponse.class)))
				.thenReturn(AddressValidationResponse.builder().erro(true).build());

		assertThrows(AddressNotFoundException.class, () -> {
			addressService.buildAddress(dto);
		});
	}
}
