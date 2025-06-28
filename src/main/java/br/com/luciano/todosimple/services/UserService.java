package br.com.luciano.todosimple.services;

import java.util.List;
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
	
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return this.userRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public User findById(Long id) {
		Optional<User> optional = this.userRepository.findById(id);
		return optional.orElseThrow(() -> new RuntimeException("Usuário não encontrado! Id: " + id
				+ ", Tipo: " + User.class.getName()));
	}
	
	/* @Transactional nos métodos que precisam de transação, por exemplo: salvar, alterar, 
	 * excluir, etc., pois assim você garante que eles vão ser executados dentro um contexto
	 * transacional e o rollback será feito caso ocorra algum erro.*/
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
