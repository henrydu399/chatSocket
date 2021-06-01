/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.system.out.serverchat.Sokect;

import co.system.out.serverchat.models.Client;
import co.system.out.serverchat.models.Menssage;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henry
 */
public class app {
    
    
 static ServerSocket ssckt;  
 static Socket sckt;  
 static DataInputStream dtinpt;  
 static DataOutputStream dtotpt; 
 
 static List<Client> listClientes;
    
 
 
 public static void main(String args[]) {  
  try {  
      ssckt = new ServerSocket(3000);
      listClientes = new ArrayList<>();
      
      java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() {
              
              while (true) {                  
                  accion();
              }
          }
      });
      
    
      
  } catch (IOException ex) {Logger.getLogger(app.class.getName()).log(Level.SEVERE, null, ex);
} 
  
 }
 
 private static void accion(){
     
       try {
           System.out.println("ESCUCHANDO CLIENTES .... ");
          String msgin = ""; 
          
          sckt = ssckt.accept();
          dtinpt = new DataInputStream(sckt.getInputStream());
          dtotpt = new DataOutputStream(sckt.getOutputStream());
          while (!msgin.equals("exit")) {
              msgin = dtinpt.readUTF();
              // CONVIRTIENDO EL MSJ A OBJECTO
              Gson gson = new Gson();
              Menssage msj = gson.fromJson(msgin, Menssage.class);
              if( msj != null ){
                  if(msj.isIsLogin() ){ // EL CLIENTE SE ESTA LOGIN
                      System.out.println("Cliente : "+msj.getClientEmisor().getIp() + " ENVIA :" + msj.getMesaje());
                      listClientes.add(msj.getClientEmisor());
                  }else{ // SE VA A ENVIAR MENSAJE A OTRO CLEINTE 
                      
                  }
                  
              }
                  
              
          }
          
          Thread.sleep(1000);
      } catch (Exception e) {
      e.printStackTrace();
      }
     
     
 }
    
}
