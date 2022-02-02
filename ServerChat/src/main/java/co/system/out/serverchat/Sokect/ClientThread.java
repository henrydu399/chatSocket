package co.system.out.serverchat.Sokect;

import java.io.*;
import java.net.*;
import java.util.*;

import com.google.gson.Gson;

import co.system.out.serverchat.models.Client;
import co.system.out.serverchat.models.Menssage;

public class ClientThread extends Thread {

	private Socket socket;
	private AppServer appServer;

	private Client cliente;
	
	DataInputStream dtinpt ;
	DataOutputStream dtotpt;
	
	BufferedReader reader;
	
	public ClientThread(Socket socket, AppServer _appServer) {
		this.socket = socket;
		this.appServer = _appServer;
		
		try {
			 this.dtinpt = new DataInputStream(socket.getInputStream());
			 //this.dtotpt = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	public void run() {
		try {
		
			String msgin = null;
			while (true) {			
				msgin = this.dtinpt.readUTF();
				//reader = new BufferedReader(new InputStreamReader(dtinpt));				
				if (msgin != null && !msgin.equals("")) {
					//INTENTAMOS OBTENER EL MENSAJE COMO UN TYPO MENSSAGE 
					try{
						Gson gson = new Gson();
						Menssage msj = gson.fromJson(msgin, Menssage.class);
						if (msj != null) {
							executeComportamiento(msj);
						}else {
							System.out.println("SERVER : " + msgin) ;
						}
					}catch (Exception e) {
						// TODO: handle exception
					}

				}			
			}

		} catch (IOException ex) {
			System.out.println("Error in UserThread: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	
	/*
	 * Metodo que ejecuta el comportamiento segun el tipo de mensaje enviado por el cliente 
	 */
	public void  executeComportamiento (Menssage msj) {
		String out = null;
		switch (msj.getType()) {
		case USERLOGIN:
			this.cliente = msj.getClientEmisor();
			System.out.println("SERVER : EL Cliente : " + msj.getClientEmisor().getUser().getNombres()+ " ENVIA :" + msj.getMesaje());
			break;
		case USERMENSSAGE:  // MENSAJE ENVIADO A OTRO OSUARIO
			out = this.appServer.enviarMensajeUserxUser(msj);
			break ;

		default:
			break;
		}
		
	}
	
	
	/*
	 * Metodo que envia mensaje desde otro usuario 
	 */
	public void  enviarMensajeCliente( Menssage msj) {
 		try {
 			if( this.socket != null) {
 				dtotpt = new DataOutputStream(this.socket.getOutputStream());
 		 		dtotpt.writeUTF(new Gson().toJson(msj));
 				dtotpt.flush();
 			}else {
 				System.out.println("SERVER : EL SOCKET ESTA CERRADO PARA EL CLIENTE :");
 			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public Client getCliente() {
		return cliente;
	}



	public void setCliente(Client cliente) {
		this.cliente = cliente;
	}



	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}



}
