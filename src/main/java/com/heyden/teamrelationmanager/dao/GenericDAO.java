package com.heyden.teamrelationmanager.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <T, PK extends Serializable> {

	public void save(T dataObject);
	
	public void update(T dataObject);
	
	public void create(T dataObject);
 
	public T get(PK id);
    
	public List<T> getAll();
	
	public List<T> getAllOrderBy(String orderByColumnName);

	public void delete(T dataObject);
    
	public void deleteById(PK id);
}