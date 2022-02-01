/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.system.out.clientchat;

import co.system.out.clientchat.utils.UtilServerChat;
import co.system.out.serverchat.models.Client;
import co.system.out.serverchat.models.Menssage;
import co.system.out.serverchat.models.User;
import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.net.Socket;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author henry
 */
public class MaincClient  extends Thread{

	private Socket sckt;


	private Client ClienteMain;

	private BufferedReader br;
	
    private boolean isLogin = false;
    
	private User userLocal = null;
	
	private String ip = null;



	public void run() {	
				menu();
	}

	
	private  void menu() {
		try {
			System.out.println(" BIENVENIDO A AL CLIENTE SYSTEM OUT");
			System.err.println(" MENU : ");
			System.err.println(" 1.CONECTAR AL SERVIDOR ");
			System.err.println(" 2.ENVIAR MENSAJE ");
			System.out.println("");
			br = new BufferedReader(new InputStreamReader(System.in));
			int opc = Integer.parseInt(br.readLine());
			switch (opc) {
			case 1:
				Conectar();
				break;

			default:
				menu();
				break;

			}

		} catch (IOException ex) {
			System.err.println("ELIJA UNA OPCION CORRECTA");
			Logger.getLogger(MaincClient.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	

	private  void Conectar() {
		try {
			// Optenemos la ip del cliente 
			this.ip=UtilServerChat.getIP();		 
			 
			sckt = new Socket("192.168.1.25", 3000);
			
			if( sckt != null) {
				new ReadThread(sckt,  this).start();
	            new WriteThread(sckt,  this).start();
			}else {
				System.err.println(" NO SE PUDO CONECTAR CON EL SERVIDOR   ");
			}
			

		} catch (IOException ex) {
			System.err.println(" NO SE PUDO CONECTAR CON EL SERVIDOR   ");
			Logger.getLogger(MaincClient.class.getName()).log(Level.SEVERE, null, ex);
			menu();
		}

	}
	
    public Menssage login() {

    	Menssage out = null;
		try {
			System.err.println(" INGRESE USUARIO  : ");
			br = new BufferedReader(new InputStreamReader(System.in));
			String user = br.readLine();
			System.err.println(" INGRESE PASSWORD  : ");
			String password = br.readLine();
			System.err.println(" INGRESE NOMBRES  : ");
			String nombres = br.readLine();
			System.err.println(" INGRESE APPELIDOS  : ");
			String apellidos = br.readLine();
			
			userLocal = new User(user, password, nombres, apellidos, 27, "0000000");
			
			Client thisCliente = new Client (this.ip , userLocal, new Date());
			Menssage msj = new Menssage(thisCliente, null, "USUARIO  : " + userLocal.getEmail(), Menssage.typeMessages.USERLOGIN);
			
			out = msj;

			
		} catch (IOException ex) {
			Logger.getLogger(MaincClient.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return out;
	}

	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public Client getClienteMain() {
		return ClienteMain;
	}

	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public User getUserLocal() {
		return userLocal;
	}

	public void setUserLocal(User userLocal) {
		this.userLocal = userLocal;
	}

	public void setClienteMain(Client clienteMain) {
		ClienteMain = clienteMain;
	}
    
    
    

	

}
