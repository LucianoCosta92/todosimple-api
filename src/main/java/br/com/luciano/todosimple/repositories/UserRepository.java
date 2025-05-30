package br.com.luciano.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.luciano.todosimple.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
