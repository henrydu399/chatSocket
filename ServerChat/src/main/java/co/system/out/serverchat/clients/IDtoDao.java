package co.system.out.serverchat.clients;


import java.util.List;

import com.gosystem.commons.exceptions.HomeException;



public interface IDtoDao<T> {
	
	List<T> getAll ()throws HomeException;
	
	void  save(T u)  throws HomeException;
	
	void  edith(T p) throws HomeException;
	
	T  find(T p) throws HomeException;
	
	List<T>  findAll(T p) throws HomeException;
	
	void  delete(T p) throws HomeException;
	
	public void desactivate(T usuario) throws HomeException ;
		
	
	
	

}
