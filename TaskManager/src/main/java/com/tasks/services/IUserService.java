package com.tasks.services;

import java.util.List;

import com.tasks.entities.User;

public interface IUserService {

	void save(User u);

	void delete(User u);

	void update(User u);

	User findById(int id);

	List<User> findAll();
	User findUserByCredentials(String username,String password);

}