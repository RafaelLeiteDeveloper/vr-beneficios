package com.vr.beneficios.vrbeneficios.domain.exception;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;
    private String message;
    private JsonNode body;

}
