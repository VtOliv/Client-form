package com.project.forms.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private HttpStatus httpStatusCode;
	private String code;
	private String message;
	private String description;

	public BusinessExceptionBody getOnlyBody() {
		return BusinessExceptionBody.builder().code(this.code).message(this.message).description(this.description)
				.build();
	}

	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class BusinessExceptionBody {
		private String code;
		private String message;
		private String description;
	}
}