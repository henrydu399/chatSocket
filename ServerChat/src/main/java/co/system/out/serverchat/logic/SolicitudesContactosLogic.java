
package co.system.out.serverchat.logic;

import co.system.out.chatsocket.general.models.Client;
import co.system.out.chatsocket.general.models.Menssage;
import co.system.out.serverchat.Sokect.ClientThread;
import co.system.out.serverchat.builds.UserBuild;
import co.system.out.serverchat.entitys.Solicitudes;
import co.system.out.serverchat.entitys.Users;
import com.gosystem.commons.exceptions.BasicExeption;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SolicitudesContactosLogic {
    
      private ClientThread clientThread;

    public SolicitudesContactosLogic(ClientThread clientThread) {
        this.clientThread = clientThread;
    }
      
      
      
    
     /**
     * Metodo que acepta una solicitud de contaco 
     */
    public void aceptarSolicitud(Menssage msj){
        Long idSolicitud = Long.valueOf(msj.getMesaje());
          try {
              Solicitudes s =   this.clientThread.getSocilitudesBusiness().getById(idSolicitud);    
              if( Objects.nonNull(s)){   
                  if( Objects.isNull(msj.getClientEmisor()) ||  Objects.isNull( msj.getClientEmisor().getUser() ) || Objects.isNull(msj.getClientEmisor().getUser().getUserId())   ){
                       this.clientThread.enviarMesajeGlobal("No se ha enviado un cliente emisor  :" +msj.getMesaje()); 
                       return;
                  }           
                  if( s.getIdUser().getId() == msj.getClientEmisor().getUser().getUserId()){
                        this.clientThread.getAppServer().getEm().getTransaction().begin();
                        
                        //Creamos primer contacto 
                        this.clientThread.getContactosBusiness().addContacto(s.getIdUser().getId(), s.getEmail());
                        
                        //Creamos el segundo contacto
                        Users u = this.clientThread.getUserBusiness().findByEmail(s.getEmail());
                        this.clientThread.getContactosBusiness().addContacto( u.getId(),  s.getIdUser().getEmail()) ;
                        
                        this.clientThread.getSocilitudesBusiness().delete(s);
                        
                       
                        
                        //Enviar mensaje con los nuevos contactos
                        this.clientThread.getLogic().enviarContactosActuales(msj.getClientEmisor().getUser() );
                        //Enviar mensaje con los nuevas solicitudes
                        this.clientThread.getLogic().enviarSolicitudesActuales();
                        
                         //ENviar los nuevos contactos a la nuev apersona
                         Menssage msjToContacto = new Menssage(this.clientThread.getCliente(), new Client("",UserBuild.build(s.getIdUser()) ,new Date()), Menssage.StatusCode.OK,"", Menssage.typeMessages.VER_CONTACTOS);
                         this.clientThread.enviarMensajeCliente(msjToContacto);
                         //
                        
                        this.clientThread.getAppServer().getEm().getTransaction().commit();
                        
                        this.clientThread.enviarMesajeGlobal("Se ha agregado el nuevo contacto:" + s.getEmail()); 
                  }else{
                      this.clientThread.enviarMesajeGlobal("No corresponde el cliente con la solicitud :" +msj.getMesaje()); 
                  }   
              }else{
                  this.clientThread.enviarMesajeGlobal("No existe la solicitud de contacto :" +msj.getMesaje()); 
              }
              
              
          } catch (BasicExeption ex) {
                 this.clientThread.getAppServer().getEm().getTransaction().rollback();
              Logger.getLogger(SolicitudesContactosLogic.class.getName()).log(Level.SEVERE, null, ex);
          }
       
       
        
    }
    
    /**
     * Metodo que crea una solicitud de contacto nuevo a un usuario
     * @param msj
     */
    public void solicitudContacto(Menssage msj){
        Users usersAfitrion = this.clientThread.getUserBusiness().findByEmail(msj.getMesaje());
        if( Objects.nonNull(usersAfitrion)){
            try {
                Users users = this.clientThread.getUserBusiness().findById(msj.getClientEmisor().getUser().getUserId());
                this.clientThread.getSocilitudesBusiness().crear( usersAfitrion.getId()  , users);
                this.clientThread.enviarMesajeGlobal("Se ha enviado la solicitud de contacto, cuando te acepte se agregara como contacto automaticamente .");
            } catch (BasicExeption ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);   
                this.clientThread.enviarMesajeGlobal(ex.getLogicalError());   
            }
        }else{
            this.clientThread.enviarMesajeGlobal("No se encontro el usuario con el correo :" +msj.getMesaje());
        }      
    }
    
    public void adiccionarContacto(Menssage msj){
        
    }
    
    
}
