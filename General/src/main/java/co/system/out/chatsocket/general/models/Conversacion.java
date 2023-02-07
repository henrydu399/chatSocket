
package co.system.out.chatsocket.general.models;

import java.util.ArrayList;
import java.util.List;


public class Conversacion {
    
    private User user;
    List<MenssageConversacion> Menssages;

    
    
    public Conversacion(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MenssageConversacion> getMenssages() {
        return Menssages;
    }

   public void addMenssage(String fecha , String msj){
       if (this.Menssages == null ){
           this.Menssages =  new ArrayList<>();
       }
       this.Menssages.add( new MenssageConversacion(fecha ,msj ));
   }
    
    
    
        public static class MenssageConversacion{
          private String msj;
          private String fecha;

            public MenssageConversacion( String fecha , String msj) {
                this.msj = msj;
                this.fecha = fecha;
            }

        public String getMsj() {
            return msj;
        }

        public String getFecha() {
            return fecha;
        }
            
            
          
          
        }     
            
    
}
