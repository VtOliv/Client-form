package com.project.forms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.forms.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{
	
}
