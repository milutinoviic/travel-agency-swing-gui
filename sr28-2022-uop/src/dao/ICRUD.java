package dao;

import java.util.ArrayList;

public interface ICRUD<T> {
	
	public T create(T entity);
	public T delete(T entity);
	public T get(int id);
	public ArrayList<T> getAll();
	public T update(T entity);
	
}
