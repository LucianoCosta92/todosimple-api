package br.com.luciano.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.luciano.todosimple.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
	List<Task> findByUser_Id(Long id);
	
	// JPQL
	//@Query(value = "Select t from Task t where t.user.id = :user_id")
	// List<Task> findByUser_Id(@Param("user_id") Long id);
	
	// @Query(value = "select * from tb_task t where t.user_id = :id", nativeQuery = true)
	// List<Task> findByUser_Id(@Param("user_id") Long id);
}
