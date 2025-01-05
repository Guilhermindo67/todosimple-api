package com.guilhermealmeida.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guilhermealmeida.todosimple.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

    List<Task> findByUser_Id(Long id);
    
    //@Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
    //List<Task> findbyUser_id(@Param("id") Long id);

    //@Query(value="SELECT * FROM task t WHERE t.user_id = :id" ,nativeQuery = true)
    //List<Task> findbyUser_id(Long id);
}