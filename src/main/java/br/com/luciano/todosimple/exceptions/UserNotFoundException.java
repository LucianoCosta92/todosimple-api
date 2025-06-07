package br.com.luciano.todosimple.exceptions;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -5354693985607801522L;

	public UserNotFoundException(Long id) {
		super(String.format("Usuário com id: %d não encontrado!", id));
	}
	
	
}
