/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.system.out.clientchat;

import co.system.out.serverchat.models.Client;
import co.system.out.serverchat.models.Menssage;
import co.system.out.serverchat.models.User;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;

/**
 *
 * @author henry
 */
public class Main {
    
 static Socket sckt;  
 static DataInputStream dtinpt;  
 static DataOutputStream dtotpt; 
 
 static Client ClienteMain;
 
 static BufferedReader br;
    
    public static void main(String args[]) {  
  java.awt.EventQueue.invokeLater(new Runnable() {  
   public void run() {  
    menu();
   }  
  });  

 }
    
    
    private static void  menu(){
      try {
          System.out.println(" BIENVENIDO A AL CLIENTE SYSTEM OUT");
          System.err.println(" MENU : ");
          System.err.println(" 1.CONECTAR AL SERVIDOR ");
          System.out.println("");
          br = new BufferedReader(new InputStreamReader(System.in));
          int opc = Integer.parseInt(br.readLine());
          switch(opc){
              case 1 :
                  Conectar();
                  break;
                  default:
                      menu();
                      break;
          
      }
          
      } catch (IOException ex) {
          System.err.println("ELIJA UNA OPCION CORRECTA");
          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
      }
        
    }
    
    private static  void Conectar(){
      try {
          sckt = new Socket("127.0.0.1", 3000); 
          System.err.println(" INGRESE USUARIO  : ");
          br = new BufferedReader(new InputStreamReader(System.in));
          String user = br.readLine();
          System.err.println(" INGRESE PASSWORD  : ");
          String password = br.readLine();
          if ( user != null && !user.equals("")  
           && password != null && !password.equals("")       ){
             login(user, password); 
          }else{
            System.err.println(" LOS DATOS INGRESADOS SON INVALIDOS !  "); 
            Conectar();
          }
      } catch (IOException ex) {
          System.err.println(" NO SE PUDO CONECTAR CON EL SERVIDOR   "); 
          Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
          menu();
      }      
        
    }
    
    
    private static void  login(String userStri ,  String passwordStrin){
        
     try {
         dtinpt = new DataInputStream(sckt.getInputStream());
         dtotpt = new DataOutputStream(sckt.getOutputStream());
         
         User user = new User(userStri,passwordStrin, "jose henry", "duarte", 27, "1090472353");
         Menssage msj = new Menssage(new Client("192.168.1.29", user, new Date()), null, "Login", true) ;
                 dtotpt.writeUTF(new Gson().toJson(msj));
     } catch (IOException ex) {
         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
    
    private  static void   init(){
          try {  
   sckt = new Socket("127.0.0.1", 3000);  
   dtinpt = new DataInputStream(sckt.getInputStream());  
   dtotpt = new DataOutputStream(sckt.getOutputStream());  
   
   /// ENVIANDO MENSAJE AL SERVER
    dtotpt.writeUTF("HOLA DESDE EL CLIENTE 1"); 
   
   String msgin = "";  
   while (!msgin.equals("Exit")) {  
    msgin = dtinpt.readUTF();  
       System.out.println(" MENSAJE DE SERVDIDOR : " + msgin); 
   }  
   
  } catch (Exception e) {}  
    }
    
}
