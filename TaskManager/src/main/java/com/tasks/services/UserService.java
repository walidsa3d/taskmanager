package com.tasks.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.tasks.entities.User;


@Stateless
public class UserService implements IUserService{
	@PersistenceContext
	EntityManager em;
	public void save(User u) {
		em.persist(u);
		
	}

	public void delete(User u) {
		em.remove(u);
		
	}

	public void update(User u) {
		em.merge(u);
		
	}

	public User findById(int id) {
		return em.find(User.class, id);
	}

	public List<User> findAll() {
		TypedQuery<User> query=em.createNamedQuery("user.findAll",User.class);
		return query.getResultList();
	}
	public User findUserByCredentials(String username,String password){
		TypedQuery<User> query=em.createNamedQuery("user.findByCreds",User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<User> results = query.getResultList();
	    if (results.isEmpty()) return null;
	    else if (results.size() == 1) return results.get(0);
	    return null;
	}
	

}
