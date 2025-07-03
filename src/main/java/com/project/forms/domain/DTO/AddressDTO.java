package com.project.forms.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

	    @NotBlank(message = "O logradouro é obrigatório.")
	    private String streetName;

	    @NotBlank(message = "O bairro é obrigatório.")
	    private String district;

	    @NotBlank(message = "A cidade é obrigatória.")
	    private String city;

	    @NotBlank(message = "O estado é obrigatório.")
	    @Size(min = 2, max = 2, message = "O estado deve ter 2 letras.")
	    private String state;

	    @NotBlank(message = "O CEP é obrigatório.")
	    @Pattern(regexp = "\\d{8}", message = "O CEP deve ter 8 dígitos numéricos.")
	    private String zipCode;

	    private String number;

	    private String addressComplement;
	    
	}