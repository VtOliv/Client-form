package com.project.forms.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.forms.domain.Client;
import com.project.forms.domain.DTO.ClientDTO;
import com.project.forms.service.ClientService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClientController {
	
	private final ClientService service;

	@PostMapping
	public ResponseEntity<Client> saveClient(@RequestBody ClientDTO dto) {
		
		log.info("Requisição: {} | Classe: {} | Cliente: {}", "POST" , "ClientController", dto.getName());
		
		var savedClient = service.newClient(dto);
		
		log.info("Resultado: {}", Optional.of(savedClient).isPresent()? "Sucesso" : "Falha" );
		
		return ResponseEntity.status(CREATED).body(savedClient);
	}
	

}
