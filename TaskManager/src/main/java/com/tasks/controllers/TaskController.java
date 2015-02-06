package com.tasks.controllers;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.tasks.entities.Task;
import com.tasks.services.ITaskService;

@ManagedBean
@ViewScoped
public class TaskController {
	@EJB
	private ITaskService its;
	private List<Task> tasklist;
	private Task task;
	private Task newTask;
	@ManagedProperty(value = "#{userc}")
	private UserController userc;
	
	@PostConstruct
	public void init(){
		try{
		setTasklist(its.findTasksByUserAndState(userc.getLoggedUser(),"TODO"));
		}
		catch (Exception e){
			setTasklist(Collections.<Task> emptyList());
		}
		newTask=new Task();
	}

	public List<Task> getTasklist() {
		return tasklist;
	}

	public void setTasklist(List<Task> tasklist) {
		this.tasklist = tasklist;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	public void doEdit(){
		its.update(task);
	}
	public void doSave(){
		newTask.setTaskState("TODO");
		newTask.setTaskOwner(userc.getLoggedUser());
		its.save(newTask);
		setTasklist(its.findTasksByUserAndState(userc.getLoggedUser(),"TODO"));
		newTask=new Task();

	}
	public void doRemove(){
		its.delete(task);
	}
	public void doUpdate(){
		its.update(task);
	}

	public Task getNewTask() {
		return newTask;
	}

	public void setNewTask(Task newTask) {
		this.newTask = newTask;
	}

	public UserController getUserc() {
		return userc;
	}

	public void setUserc(UserController userc) {
		this.userc = userc;
	}
	public void doDone(){
		task.setTaskState("DONE");
		its.update(task);
		setTasklist(its.findTasksByUserAndState(userc.getLoggedUser(),"TODO"));
	}
}
