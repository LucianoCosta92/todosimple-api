package br.com.luciano.todosimple.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(status, errors);
		
		return new ResponseEntity<>(apiErrorMessage, apiErrorMessage.getStatus());
	}
	
	/* . Antes do nosso método handleUserNotFoundException, adicionamos a anotação @ExceptionHandler. 
	 * 	A configuração do Spring detectará essa anotação e registrará o método como manipulador de exceção
	 * 	para a classe de argumento e suas subclasses.
	 * 
	 * . No corpo do método, estamos apenas recolhendo as informações necessárias da exceção e transformando
	 * 	o nosso objeto de retorno de erros ApiErrorMessage.
	 * */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception, WebRequest request) {
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
		
		return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
	}
	
	@ExceptionHandler(TaskNotFoundException.class)
	public ResponseEntity<Object> handleTaskNotFoundException(TaskNotFoundException exception, WebRequest request) {
		ApiErrorMessage apiErrorMessage = new ApiErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
		
		return new ResponseEntity<>(apiErrorMessage, new HttpHeaders(), apiErrorMessage.getStatus());
	}


}
