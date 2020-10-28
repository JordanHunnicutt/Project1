package com.web.repository;

import java.util.List;

public interface DaoContract<T,I>{

	List<T> findAll();
	T findById(I i);
	T findByName(String name);
	int create(T t);
	int update(T t);
	int delete(I i);
	
	
}
