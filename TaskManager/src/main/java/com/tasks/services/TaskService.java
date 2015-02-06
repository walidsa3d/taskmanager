package com.tasks.services;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.tasks.entities.Task;
import com.tasks.entities.User;

@Stateless
public class TaskService implements ITaskService {
@PersistenceContext
EntityManager em;


public void save(Task t){
	em.persist(t);
};

public void delete(Task t){
	em.remove(t);
};

public void update(Task t){
	em.merge(t);
};

public Task findById(int id){
	return em.find(Task.class, id);
}

public List<Task> findAll(){
	TypedQuery<Task> query=em.createNamedQuery("task.findAll",Task.class);
	return query.getResultList();
}
public List<Task> findTasksByUserAndState(User user, String state){
	TypedQuery<Task> query=em.createNamedQuery("task.findByUserAndState",Task.class);
	query.setParameter("id", user.getUserId());
	query.setParameter("state", state);
	return query.getResultList();
}

public List<Task> findTasksByState(String state) {
	TypedQuery<Task> query=em.createNamedQuery("task.findByState",Task.class);
	query.setParameter("state", state);
	return query.getResultList();
}

}
