/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.system.out.clientchatgui.main;


import co.system.out.clientchatgui.utils.UtilServerChat;
import co.system.out.clientchatgui.views.MainView;
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




public class MaincClient  extends Thread{

	private Socket sckt;

	private Client ClienteMain;

	private BufferedReader br;
	
       private boolean isLogin = false;
    
	private User userLocal = null;
	
	private String ip = null;
        
        private ReadThread readThread;
                
        private WriteThread writeThread;
        
        MainView mainView;
        

    


	public void run() {	
            /*
            OBTENEMOS LA IP PUBLICA DEL CLIENTE 
            */
            this.ip = UtilServerChat.getIP();
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

	

	public  void Conectar() {
		try {
			// Optenemos la ip del cliente 
			this.ip=UtilServerChat.getIP();		 
			//sckt = new Socket("hdhome.access.ly", 3000);	
                        sckt = new Socket("127.0.0.1", 3000);
			if( sckt != null) {
				readThread = new ReadThread(sckt,  this);
                                readThread.start();
				System.err.println("    ");
	                        writeThread = new WriteThread(sckt,  this);
                                writeThread.start();
                                System.err.println(" CONEXION CON EL SERVIDOR COMPLETA  ");
			}else {
				System.err.println(" NO SE PUDO CONECTAR CON EL SERVIDOR   ");
			}
			

		} catch (IOException ex) {
			System.err.println(" NO SE PUDO CONECTAR CON EL SERVIDOR   ");
			Logger.getLogger(MaincClient.class.getName()).log(Level.SEVERE, null, ex);
			menu();
		}

	}
	
//    public Menssage login() {
//
//    	Menssage out = null;
//		try {
//			System.err.println(" INGRESE USUARIO  : ");
//			br = new BufferedReader(new InputStreamReader(System.in));
//			String user = br.readLine();
//			System.err.println(" INGRESE PASSWORD  : ");
//			String password = br.readLine();
//			System.err.println(" INGRESE NOMBRES  : ");
//			String nombres = br.readLine();
//	//		System.err.println(" INGRESE APPELIDOS  : ");
//	//		String apellidos = br.readLine();
//			
//			userLocal = new User(user, password, nombres, "", 27, "0000000");
//			
//			Client thisCliente = new Client (this.ip , userLocal, new Date());
//			Menssage msj = new Menssage(thisCliente, null, "USUARIO  : " + userLocal.getEmail(), Menssage.typeMessages.USERLOGIN);
//			
//			out = msj;
//
//			
//		} catch (IOException ex) {
//			Logger.getLogger(MaincClient.class.getName()).log(Level.SEVERE, null, ex);
//		}
//		
//		return out;
//	}

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

    public MainView getMainView() {
        return mainView;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }
        
        
        
        

    public ReadThread getReadThread() {
        return readThread;
    }

    public void setReadThread(ReadThread readThread) {
        this.readThread = readThread;
    }

    public WriteThread getWriteThread() {
        return writeThread;
    }

    public void setWriteThread(WriteThread writeThread) {
        this.writeThread = writeThread;
    }
    
    
    

	

}