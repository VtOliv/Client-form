package com.project.forms.domain;

import java.io.Serializable;

import com.project.forms.domain.Enum.ClientType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

	private static final long serialVersionUID = -8873072552261436112L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long clientId;
	
	@Enumerated(EnumType.STRING)
	public ClientType clientType;
	
	public String clientCPF;
	
	public String clientCNPJ;
	
	public String name;
	
	public String email;
	
	public String mobileTelephone;
	
	public String fixedTelephone;
	
	@OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	private Address address;
}
