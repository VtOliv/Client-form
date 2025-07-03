package com.project.forms.exception;

import org.springframework.http.HttpStatus;

public class AddressNotFoundException extends BusinessException {

	private static final long serialVersionUID = 409463908036841929L;

	public AddressNotFoundException() {
		super.setHttpStatusCode(HttpStatus.BAD_REQUEST);
		super.setMessage("Cep inválido foi informado");
	}
}
