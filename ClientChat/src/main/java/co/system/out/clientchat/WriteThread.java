package co.system.out.clientchat;

import java.awt.TrayIcon.MessageType;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;


import co.system.out.clientchat.utils.UtilServerChat;
import co.system.out.serverchat.models.Client;
import co.system.out.serverchat.models.Menssage;
import co.system.out.serverchat.models.User;
import co.system.out.serverchat.models.Menssage.typeMessages;
 
/**
 * This thread is responsible for reading user's input and send it
 * to the server.
 * It runs in an infinite loop until the user types 'bye' to quit.
 *
 * @author www.codejava.net
 */
public class WriteThread extends Thread {
	

    private Socket socket;

    static DataOutputStream dtotpt;
    
	private BufferedReader br;
	
	private MaincClient mainClient;
 
    public WriteThread(Socket socket , MaincClient _mainClient ) {
        this.socket = socket;
        this.mainClient = _mainClient;
 
      
    }
 
    public void run() {
 
    	try {
    		
    	String mensaje = "";
   		dtotpt = new DataOutputStream(this.socket.getOutputStream());
		br = new BufferedReader(new InputStreamReader(System.in));
   		
			do {
	
				
				if ( this.mainClient.isLogin() == false ) { // SI ES EL PRIMER MENSAJE ENTONCES ES LOGIN DEL CLIENTE 
					 Menssage msj = mainClient.login();
					 dtotpt.writeUTF(new Gson().toJson(msj));
					 dtotpt.flush();
					 this.mainClient.setLogin(true);
				}else {
					System.err.println(" INGRESE EMAIL : ");
					String email;
					email = br.readLine();
		
					System.err.println(" INGRESE MENSAJE : ");
					mensaje = br.readLine();
		
					User userRecept = new User(email, null, null, null, 0, "0000000"); // usuario receptor
					Menssage msj = new Menssage(new Client(  this.mainClient.getIp() ,this.mainClient.getUserLocal(),new Date()), // CLIENTE EMISOR 
							                    new Client(null, userRecept, new Date()), // CLIENTE RECEPTOR 
							                    mensaje ,
							                    typeMessages.GLOBALMENSSAGE.USERMENSSAGE); //
		
					dtotpt.writeUTF(new Gson().toJson(msj)  );
					dtotpt.flush();
				}
		

	
			} while (!mensaje.equals("bye"));
        
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
 
        try {
            socket.close();
        } catch (IOException ex) {
 
            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
    





    

}