package co.system.out.serverchat.Sokect;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import co.system.out.serverchat.models.Client;
import co.system.out.serverchat.models.Menssage;
import co.system.out.serverchat.models.User;
import co.system.out.serverchat.models.Menssage.typeMessages;

public class BrocastClientMessage extends Thread {
	
	
	private AppServer app;
	
	private   BufferedReader br;
	
	

	public BrocastClientMessage(AppServer app) {
		super();
		this.app = app;
	}

	/*
	 * Se envia un mensaje del servidor  a todos los clientes 
	 * @in lista de clientes conectados
	 * 
	 */
	public void run () {
		enviarMensaje ();	
	}
	
	private   void  enviarMensaje () {
		
		/// ENVIAR MENSAJE A LOS CLIENTES
				System.out.println(" INGRESE MENSAJE A ENVIAR A CLIENTES   : ");
				br = new BufferedReader(new InputStreamReader(System.in));
				try {
					String mensaje = br.readLine();
					enviar (mensaje);					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		//enviarMensaje ();
	}
	
	private void  enviar (String mensaje ) {
		
		for ( ClientThread tempClientThread : this.app.getListClientThread()) {
			
			try {
				DataOutputStream dtotpt = new DataOutputStream(tempClientThread.getSocket().getOutputStream());	
				Menssage msj = new Menssage(new Client(  null, null , new Date()), null, mensaje , Menssage.typeMessages.GLOBALMENSSAGE);
				dtotpt.writeUTF(new Gson().toJson(msj)  );
				dtotpt.flush();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
