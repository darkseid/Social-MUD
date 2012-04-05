/**
 * 
 */
package com.smud.service.data;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author apontador
 * TODO move the methods hashfy and objetfy to a AbstractClass, and give them better names
 */
public interface Repository<T> {
	
	/**
	 * Transforms the object into a hashtable, ready to be persisted
	 * 
	 * @param t
	 * @return
	 */
	Hashtable<String, String> hashfy(T t);
	
	/**
	 * Transforms a hash into an object
	 * 
	 * @param hashfiedObject
	 * @return
	 */
	T objectfy(Map<Object, Object> hashfiedObject);

	/**
	 * Perists the Object T
	 * 
	 * @param t
	 * @return
	 */
	boolean store(T t);
	
	/**
	 * Gets the object with ID == <b>id</b>
	 * 
	 * @param id
	 * @return
	 */
	T getById(long id);
	
	/**
	 * Gets the next available id
	 * 
	 * @return
	 */
	long nextId();
}
