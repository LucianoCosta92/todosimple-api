package br.com.luciano.todosimple.exceptions;

public class TaskNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 6133706259618833718L;

	public TaskNotFoundException(Long id) {
		super(String.format("Tarefa com Id: %d não encontrada!", id));
	}
	
	

}
