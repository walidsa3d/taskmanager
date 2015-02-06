package com.tasks.services;

import java.util.List;

import com.tasks.entities.Task;
import com.tasks.entities.User;

public interface ITaskService {

	void save(Task t);

	void delete(Task t);

    void update(Task t);

	Task findById(int id);

	List<Task> findAll();
	List<Task> findTasksByUserAndState(User user,String state);
	List<Task> findTasksByState(String state);
	

}