

package co.system.out.clientchatgui.utils;

import co.system.out.chatsocket.general.models.Client;
import co.system.out.chatsocket.general.models.Menssage;




public class MenssageUtil {
    
    
    
    public static Menssage create(Client clientEmisor , Client clientReceptor , String mensaje , Menssage.typeMessages type){
        Menssage m = new Menssage(clientEmisor, clientReceptor, mensaje, type); 
        return m;      
    }
    
    
    
}
