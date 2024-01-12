/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.system.out.serverchat.dao;

import com.gosystem.commons.exceptions.GenericException;
import java.util.List;


public interface IDao<T>  {
    
    	List<T> getAll ()throws GenericException;
	
	void  save(T u)  throws GenericException;
	
	void  edith(T p) throws GenericException;
	
	T  find(T p) throws GenericException;
	
	List<T>  findAll(T p) throws GenericException;
	
	void  delete(T p) throws GenericException;
	
	public void desactivate(T usuario) throws GenericException ;
}
