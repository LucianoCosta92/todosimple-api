package br.com.luciano.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luciano.todosimple.models.Task;
import br.com.luciano.todosimple.models.User;
import br.com.luciano.todosimple.repositories.TaskRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserService userService;
	
	@Transactional(readOnly = true)
	public List<Task> findAll() {
		return this.taskRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Task findById(Long id) {
		Optional<Task> task = this.taskRepository.findById(id);
		return task.orElseThrow(() -> new RuntimeException("Tarefa não encontrada! Id: " + id));
	}
	
	@Transactional(readOnly = true)
	public List<Task> findAllByUserId(Long userId) {
		return this.taskRepository.findByUser_Id(userId);
	}
	
	@Transactional
	public Task create(Task obj) {
		User user = this.userService.findById(obj.getUser().getId());
		obj.setId(null);
		obj.setUser(user);
		obj = this.taskRepository.save(obj);
		return obj;
	}
	
	@Transactional
	public Task update(Task obj) {
		Task newObj = findById(obj.getId());
		newObj.setDescription(obj.getDescription());
		return this.taskRepository.save(newObj);
	}
	
	@Transactional
	public void delete(Long id) {
		findById(id);
		try {
			this.taskRepository.deleteById(id);
		} catch (Exception e) {
			throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
		}
	}
	
	
}
