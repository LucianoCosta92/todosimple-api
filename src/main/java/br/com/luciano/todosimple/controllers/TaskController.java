package br.com.luciano.todosimple.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.luciano.todosimple.exceptions.TaskNotFoundException;
import br.com.luciano.todosimple.models.Task;
import br.com.luciano.todosimple.services.TaskService;
import br.com.luciano.todosimple.services.UserService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<Task>> findAll() {
		List<Task> list = this.taskService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Task> findById(@PathVariable Long id) {
		try {
			Task obj = this.taskService.findById(id);
			return ResponseEntity.ok().body(obj);
		} catch (Exception e) {
			throw new TaskNotFoundException(id);
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Task>> findAllByUserId(@PathVariable Long userId) {
		this.userService.findById(userId);
		List<Task> objs = this.taskService.findAllByUserId(userId);
		return ResponseEntity.ok().body(objs);
	}

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Task obj) {
		this.taskService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Task obj, @PathVariable Long id) {
		obj.setId(id);
		obj = this.taskService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.taskService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
