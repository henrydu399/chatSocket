/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.system.out.serverchat.models;

/**
 *
 * @author henry
 */
public class User  extends Persona{
    
  private String email;
  private String password;
  private long userId;
  private String rol;
  private boolean state;

    public User(String email, long userId, String rol, boolean state, String nombres, String apellidos, int edad, String cedula) {
        super(nombres, apellidos, edad, cedula);
        this.email = email;
        this.userId = userId;
        this.rol = rol;
        this.state = state;
    }

    public User(String email, String password, String nombres, String apellidos, int edad, String cedula) {
        super(nombres, apellidos, edad, cedula);
        this.email = email;
        this.password = password;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
    
    
    

  
      
  
  
  
  
    
}
