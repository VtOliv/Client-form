package com.project.forms.exception;

import org.springframework.http.HttpStatus;

public class CNPJNotFoundException extends BusinessException {
	private static final long serialVersionUID = -4077623339671616919L;
	
	public CNPJNotFoundException() {
		super.setHttpStatusCode(HttpStatus.BAD_REQUEST);
		super.setMessage("CNPJ não foi informado");
	}
}
