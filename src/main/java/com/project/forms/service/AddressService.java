package com.project.forms.service;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.forms.domain.Address;
import com.project.forms.domain.AddressValidationResponse;
import com.project.forms.domain.DTO.AddressDTO;
import com.project.forms.exception.AddressNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {

    private static final String VIACEP_URL = "https://viacep.com.br/ws/{cep}/json/";

    private final RestTemplate restTemplate;

    public boolean isValidCep(String cep) {
        String url = fromUriString(VIACEP_URL)
                .buildAndExpand(cep)
                .toUriString();

        try {
            AddressValidationResponse response = restTemplate.getForObject(url, AddressValidationResponse.class);
            return response != null && !response.getErro();
        } catch (Exception e) {
            return false;
        }
    }
    
    public Address buildAddress(AddressDTO dto) {
    	
    	if (!isValidCep(dto.getZipCode())) {
    		throw new AddressNotFoundException();
    	}
    	
    	return Address.builder()
    			.zipCode(dto.getZipCode())
    			.district(dto.getDistrict())
    			.city(dto.getCity())
    			.state(dto.getState())
    			.streetName(dto.getStreetName())
    			.addressComplement(dto.getAddressComplement())
    			.build();
    }
}
