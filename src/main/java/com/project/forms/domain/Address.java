package com.project.forms.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Address implements Serializable {

	private static final long serialVersionUID = -249319100601155268L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long addressId;
	
	@OneToOne
	@JoinColumn(name = "client_id", referencedColumnName = "clientId")
	private Client client;
	
	public String zipCode;
	
	public String streetName;
	
	public String addressComplement;
	
	public String city;
	
	public String district;
	
	public String state;

}
