package com.project.forms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.forms.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
