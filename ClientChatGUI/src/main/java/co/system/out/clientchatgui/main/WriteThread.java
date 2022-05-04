package co.system.out.clientchatgui.main;

import co.system.out.clientchatgui.models.*;
import co.system.out.clientchatgui.models.Menssage.typeMessages;
import java.io.*;
import java.net.*;
import java.util.Date;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriteThread extends Thread {

    private Socket socket;

    private DataOutputStream dtotpt;

    private BufferedReader br;

    private ModelPrincipal modelPrincipal;

    public WriteThread(Socket socket, ModelPrincipal _modelPrincipal) {
        this.socket = socket;
        this.modelPrincipal = _modelPrincipal;

        try {

            this.dtotpt = new DataOutputStream(this.socket.getOutputStream());

        } catch (IOException ex) {
            Logger.getLogger(ReadThread.class.getName()).log(Level.SEVERE, "ERROR CREANDO  ESCRITURA DEL SERVER ", ex);
            ex.printStackTrace();
        }

    }

    public void enviar(long idUser , String mesaje) {
        try {          
            // public User(String email, long userId, String rol, String state, String tipoIdentificacion, String nombres, String apellidos, String numeroIdentificacion) {
            User userRecept = new User(null, idUser, null, null, null, null, null, null); // usuario receptor
                
            Menssage msj = new Menssage(
                    new Client(this.modelPrincipal.getIp(), this.modelPrincipal.getUser(), new Date()), // CLIENTE EMISOR 
                    new Client(null, userRecept, new Date()), // CLIENTE RECEPTOR 
                    mesaje,
                    typeMessages.USERMENSSAGE); //
            
            Logger.getLogger(WriteThread.class.getName()).log(Level.INFO, "ENVIANDO MENSAJE AL SERVER ...  "+ new Gson().toJson(msj));

            dtotpt.writeUTF(new Gson().toJson(msj));
            //dtotpt.flush();



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public DataOutputStream getDtotpt() {
        return dtotpt;
    }

    public void setDtotpt(DataOutputStream dtotpt) {
        this.dtotpt = dtotpt;
    }

}
