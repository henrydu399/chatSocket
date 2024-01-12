
package co.system.out.serverchat.business;

import co.system.out.chatsocket.general.models.*;
import co.system.out.serverchat.builds.UserBuild;
import co.system.out.serverchat.dao.ContactosDao;
import co.system.out.serverchat.dao.IDao;
import co.system.out.serverchat.entitys.Contactos;
import co.system.out.serverchat.entitys.Users;
import co.system.out.serverchat.exceptions.UserExceptions;
import com.gosystem.commons.exceptions.GenericException;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;


public class ContactosBusinessImpl  implements IDao, IContactosBusiness{ 

    
      private ContactosDao dao;
      
      private IUserBusiness UserBusiness;

    public ContactosBusinessImpl(EntityManager _em , IUserBusiness _UserBusiness) {
        this.dao = new ContactosDao(_em);
        this.UserBusiness = _UserBusiness;

    }
    
    @Override
    public Contactos getContactoById(Long id){
       return  this.dao.getContactoById(id);
    }
    
    @Override
    public List<User> getContactosByUser(Long idUser) throws UserExceptions {
         List<User> listUsers =  dao.getContactosByUser(idUser);
         return listUsers;
    }
    
        public boolean existeContactoCreado(Long idUSerPrincipal , String emailContacto){
             List<User> listUsers =    this.getContactosByUser( idUSerPrincipal);
             if( Objects.nonNull(listUsers)){
                 for( User u:listUsers){
                     if(u.equals(emailContacto)){
                         return true;
                     }
                 }
             }
             return false;
       }
    

    @Override
    public void addContacto(long idUser, String emailContacto) throws UserExceptions {   
        //Buscamos si existe el contacto a agrgar
        Users u = this.UserBusiness.findByEmail(emailContacto);
        User userByAdd = UserBuild.build(u);
        //Buscamos el usuario principal 
        Users userPrincipal = this.UserBusiness.findById(idUser);
        this.dao.addContacto(userByAdd, userPrincipal );      
    }

    @Override
    public List getAll() throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void save(Object u) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void edith(Object p) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object find(Object p) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List findAll(Object p) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object p) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void desactivate(Object usuario) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
    
    
    
}
