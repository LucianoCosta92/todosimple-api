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

import br.com.luciano.todosimple.exceptions.UserNotFoundException;
import br.com.luciano.todosimple.models.User;
import br.com.luciano.todosimple.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	/* A ResponseEntity permite que você tenha controle total sobre 
	 * a resposta HTTP que o seu servidor envia, incluindo o status
	 * (ex: 200 OK, 404 Not Found), os cabeçalhos (ex: Content-Type, 
	 * Location) e o corpo da resposta (ex: dados JSON, XML).*/
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = this.service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		try {
			User obj = this.service.findById(id);
			return ResponseEntity.ok().body(obj);
		} catch (Exception e) {
			throw new UserNotFoundException(id);
		}
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody User obj) {
		this.service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody User obj, @PathVariable Long id) {
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
