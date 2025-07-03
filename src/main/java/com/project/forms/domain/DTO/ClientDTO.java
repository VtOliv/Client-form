package com.project.forms.domain.DTO;

import com.project.forms.domain.Enum.ClientType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ClientDTO {
	
    @NotNull(message = "O tipo do cliente é obrigatório.")
    private ClientType clientType;

    @Pattern(
        regexp = "\\d{11}",
        message = "O CPF deve conter 11 dígitos numéricos."
    )
    private String clientCPF;

    @Pattern(
        regexp = "\\d{14}",
        message = "O CNPJ deve conter 14 dígitos numéricos."
    )
    private String clientCNPJ;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String name;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail deve estar em formato válido.")
    private String email;

    @Pattern(
        regexp = "\\d{10,11}",
        message = "O telefone móvel deve conter apenas dígitos e ter entre 10 e 11 dígitos."
    )
    private String mobileTelephone;

    @Pattern(
        regexp = "\\d{10,11}",
        message = "O telefone fixo deve conter apenas dígitos e ter entre 10 e 11 dígitos."
    )
    private String fixedTelephone;

    @NotNull(message = "O endereço não pode ser nulo.")
    private AddressDTO address;
}