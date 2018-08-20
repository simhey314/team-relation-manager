package com.heyden.teamrelationmanager.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <T, PK extends Serializable> {

	void save(T dataObject);
 
    T get(PK id);
    
    List<T> getAll();

    void delete(T dataObject);
    
    void deleteById(PK id);
}