package com.guilhermealmeida.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilhermealmeida.todosimple.models.Task;
import com.guilhermealmeida.todosimple.models.User;
import com.guilhermealmeida.todosimple.repositories.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserService userService;

    @SuppressWarnings("rawtypes")
    @Transactional
    public List findAll(){
        List<Task> listTask = this.taskRepository.findAll();
        try {
            return listTask;        
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao buscar as Tasks:"+ e.getMessage());
        }    
    }

    @Transactional
    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException(
            "Tarefa não encontrada Id: "+id+", Tipo: "+User.class.getName()));
    }

    @Transactional
    public List<Task> findAllByUserId(Long userId) {
        List<Task> listTask = this.taskRepository.findByUser_Id(userId);
        return listTask;
    }

    @Transactional
    public Task create(Task task){
        User user = this.userService.findById(task.getUser().getId());
        task.setId(null);
        task.setUser(user);
        task = this.taskRepository.save(task);
        return task;
    }

    @Transactional
    public Task update(Task task){
        Task newTask = findById(task.getId());
        newTask.setDescription(task.getDescription());
        return this.taskRepository.save(newTask);
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois a entidades relacionadas !");    
        }
    }


}