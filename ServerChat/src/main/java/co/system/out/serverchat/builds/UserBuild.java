package co.system.out.serverchat.builds;

import co.system.out.chatsocket.general.models.*;
import co.system.out.serverchat.entitys.Users;
import java.util.Objects;

public class UserBuild {
   
    public static User build(Users u){
        User out ;
        if( Objects.nonNull(u)){
            
            String email = u.getEmail();
            Long id = u.getId();
            String rol = u.getRol();
            String state = u.getState();
          
            String userName = u.getUsername();
            
            String nombres = u.getNombre();
            String apellidos = u.getApellidos();
            String tipoIdentificacion =   u.getTipoIdentificacion()==null ? "" : u.getTipoIdentificacion();
            String numeroIdentificacion =   u.getNumeroIdentificacion()== null ? "" : u.getNumeroIdentificacion();
            
            out = new User(email, id, rol, state, tipoIdentificacion, nombres, apellidos, numeroIdentificacion );
            out.setUserName(userName);
            
            return out;
        }
        return null;
    }
    
        public static Users build(User u){
        Users out ;
        if( Objects.nonNull(u)){
            out = new Users();
            out.setApellidos(u.getApellidos());
            out.setEmail(u.getEmail());
            out.setId(u.getUserId());
            out.setNombre(u.getNombres());
            out.setUsername(u.getUserName());
            out.setState(u.getState());
            out.setRol(u.getRol());
            return out;
        }
        return null;
    }
    
}
