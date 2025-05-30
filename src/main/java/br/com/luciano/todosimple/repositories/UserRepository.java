package br.com.luciano.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.luciano.todosimple.models.User;

/* O repositório é responsável por fazer operações no banco de dados, 
 * como buscar, salvar, deletar. Ele é uma interface que estende 
 * JpaRepository, e o Spring já cuida da implementação para você.*/
public interface UserRepository extends JpaRepository<User, Long> {
	
}
