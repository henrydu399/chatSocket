/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.system.out.serverchat.models;

import java.io.Serializable;

/**
 *
 * CAUDNO ES UN MENSAJE DEL SERVER LOS CLIENTES VAN NULL
 */
public class Menssage implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Client clientEmisor;
    private Client clienteReceptor;
    private String Mesaje;

    
    private typeMessages type;

    public Menssage(Client clientEmisor, Client clienteReceptor, String Mesaje , typeMessages type) {
        this.clientEmisor = clientEmisor;
        this.clienteReceptor = clienteReceptor;
        this.Mesaje = Mesaje;
        this.type =  type;
        
    }

    public Client getClientEmisor() {
        return clientEmisor;
    }

    public void setClientEmisor(Client clientEmisor) {
        this.clientEmisor = clientEmisor;
    }

    public Client getClienteReceptor() {
        return clienteReceptor;
    }

    public void setClienteReceptor(Client clienteReceptor) {
        this.clienteReceptor = clienteReceptor;
    }

    public String getMesaje() {
        return Mesaje;
    }

    public void setMesaje(String Mesaje) {
        this.Mesaje = Mesaje;
    }

  
    
    
    public typeMessages getType() {
		return type;
	}

	public void setType(typeMessages type) {
		this.type = type;
	}




	public enum typeMessages{
    	ADDUSER,
    	DELETEUSER,
    	DISCONECTUSER,
    	GLOBALMENSSAGE,
    	SERVERMENSSAGE,
    	USERMENSSAGE,
    	USERLOGIN
    }
    
    
    
    
}
