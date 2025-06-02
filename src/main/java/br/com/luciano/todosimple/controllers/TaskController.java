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

import br.com.luciano.todosimple.models.Task;
import br.com.luciano.todosimple.services.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> findById(@PathVariable Long id) {
		Task obj = this.service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Task>> findAllByUserId(@PathVariable Long userId) {
		List<Task> objs = this.service.findAllByUserId(userId);
		return ResponseEntity.ok().body(objs);
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Task obj) {
		this.service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Task obj, @PathVariable Long id) {
		obj.setId(id);
		obj = this.service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.service.delete(id);
		return ResponseEntity.noContent().build();
	}
	

	
}
