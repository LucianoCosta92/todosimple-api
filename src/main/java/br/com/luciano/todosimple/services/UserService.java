package br.com.luciano.todosimple.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.luciano.todosimple.models.User;
import br.com.luciano.todosimple.repositories.UserRepository;

/* A camada de serviço contém a lógica de negócios, 
 * como validações e regras antes de acessar o banco.*/
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findById(Long id) {
		Optional<User> optional = this.userRepository.findById(id);
		return optional.orElseThrow(() -> new RuntimeException("Usuário não encontrado! Id: " + id
				+ ", Tipo: " + User.class.getName()));
	}
	
	@Transactional
	public User create(User obj) {
		obj.setId(null);
		obj = this.userRepository.save(obj);
		return obj;
	}
	
	@Transactional
	public User update(User obj) {
		User newObj = findById(obj.getId());
		newObj.setPassword(obj.getPassword());
		return this.userRepository.save(newObj);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			this.userRepository.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
		}
	}
	
	
}
