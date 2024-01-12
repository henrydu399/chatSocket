

package co.system.out.serverchat.util;

import co.system.out.chatsocket.general.models.*;




public class MenssageUtil {
    
    
    
    public static Menssage create(Client clientEmisor , Client clientReceptor , String mensaje , Menssage.typeMessages type){
        Menssage m = new Menssage(clientEmisor, clientReceptor, mensaje, type); 
        return m;      
    }
    
    
    
}
