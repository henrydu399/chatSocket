package co.system.out.clientchat;

import java.io.*;
import java.net.*;

import com.google.gson.Gson;

import co.system.out.serverchat.models.Client;
import co.system.out.serverchat.models.Menssage;
 

public class ReadThread extends Thread {
	
    private BufferedReader reader;
    private Socket socket;

    private MaincClient mainClient;
    
    private String msgin;
    
    DataInputStream dtinpt ;
    
    public ReadThread(Socket socket , MaincClient _mainClient) {
        this.socket = socket;

        this.mainClient = _mainClient;
 

    }
 
    public void run() {
    	
        try {
        	
        	this.dtinpt = new DataInputStream(socket.getInputStream());
           
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    	
        while (true) {
            try {
            	msgin = this.dtinpt.readUTF();
               
 
        		if (msgin != null && !msgin.equals("")) {
    				Gson gson = new Gson();
    				Menssage msj = gson.fromJson(msgin, Menssage.class);
    				if (msj != null) {
    
    					ComportamientoPorType(msj);
    				}
    			}
                

            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
    
    /*
     * Metodo que controla el mensaje recibido dependiento del tipo de mensaje 
     */
    private void ComportamientoPorType( Menssage msj) {
        // prints the username after displaying the server's message
	
        	switch (msj.getType() ) {
			case GLOBALMENSSAGE:
				 System.out.println(" [ SERVIDOR : ] " + " [ " +msj.getMesaje() + " ]");
				break;
			case USERMENSSAGE :
				System.out.println(" [ "+msj.getClientEmisor().getUser().getEmail()+" ] " + " [ " +msj.getMesaje() + " ]");
				break;

			default:
				break;
			}
               
    }
}
