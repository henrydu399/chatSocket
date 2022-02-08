package co.system.out.clientchatgui.main;

import java.io.*;
import java.net.*;
import java.util.Date;

import com.google.gson.Gson;

import co.system.out.serverchat.models.Client;
import co.system.out.serverchat.models.Menssage;
import co.system.out.serverchat.models.User;
import co.system.out.serverchat.models.Menssage.typeMessages;

/**
 * This thread is responsible for reading user's input and send it to the
 * server. It runs in an infinite loop until the user types 'bye' to quit.
 *
 * @author www.codejava.net
 */
public class WriteThread extends Thread {

    private Socket socket;

    private DataOutputStream dtotpt;

    private BufferedReader br;

    private MaincClient mainClient;

    public WriteThread(Socket socket, MaincClient _mainClient) {
        this.socket = socket;
        this.mainClient = _mainClient;

    }
    
    
    public void  enviar(String email, String mesaje){
         try {
            
            dtotpt = new DataOutputStream(this.socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(System.in));
                User userRecept = new User(email, null, null, null, 0, "0000000"); // usuario receptor
                Menssage msj = new Menssage(new Client(this.mainClient.getIp(), this.mainClient.getUserLocal(), new Date()), // CLIENTE EMISOR 
                        new Client(null, userRecept, new Date()), // CLIENTE RECEPTOR 
                        mesaje,
                        typeMessages.GLOBALMENSSAGE.USERMENSSAGE); //

                dtotpt.writeUTF(new Gson().toJson(msj));
                dtotpt.flush();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

          

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void run() {

        try {

            String mensaje = "";
            dtotpt = new DataOutputStream(this.socket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(System.in));

            do {

                System.err.println(" INGRESE EMAIL O USUARIO A ENVIAR : ");
                String email;
                email = br.readLine();

                System.err.println(" INGRESE MENSAJE A ENVIAR : ");
                mensaje = br.readLine();

                User userRecept = new User(email, null, null, null, 0, "0000000"); // usuario receptor
                Menssage msj = new Menssage(new Client(this.mainClient.getIp(), this.mainClient.getUserLocal(), new Date()), // CLIENTE EMISOR 
                        new Client(null, userRecept, new Date()), // CLIENTE RECEPTOR 
                        mensaje,
                        typeMessages.GLOBALMENSSAGE.USERMENSSAGE); //

                dtotpt.writeUTF(new Gson().toJson(msj));
                dtotpt.flush();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
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

    public DataOutputStream getDtotpt() {
        return dtotpt;
    }

    public void setDtotpt(DataOutputStream dtotpt) {
        this.dtotpt = dtotpt;
    }
    
    
    
    

}
