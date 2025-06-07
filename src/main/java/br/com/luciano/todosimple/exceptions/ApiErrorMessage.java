package br.com.luciano.todosimple.exceptions;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatusCode;

/* resposta para users o status da requisição e uma lista com 
 * as descrições de erros que ocorreram. */
public class ApiErrorMessage {
	
	private HttpStatusCode status;
	
	private List<String> errors;

	public ApiErrorMessage(HttpStatusCode status, List<String> errors) {
		super();
		this.status = status;
		this.errors = errors;
	}

	public ApiErrorMessage(HttpStatusCode status, String error) {
		super();
		this.status = status;
		errors = Arrays.asList(error);
	}

	public HttpStatusCode getStatus() {
		return status;
	}

	public void setStatus(HttpStatusCode status) {
		this.status = status;
	}

	public List<String> getErrors() {
		return errors;
	}
	
	
	
	
}
