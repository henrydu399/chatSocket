package co.system.out.serverchat.dao;

import co.system.out.serverchat.exceptions.UserExceptions;
import co.system.out.serverchat.exceptions.UserExceptions.UserExceptionsMensajes;
import co.system.out.chatsocket.general.models.*;
import co.system.out.serverchat.builds.UserBuild;
import co.system.out.serverchat.entitys.Users;
import com.gosystem.commons.exceptions.GenericException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

public class UserDAO extends Dao implements IDao<Users>{

    public UserDAO(EntityManager em) {
        super(em);
    }
  

    public User getUser(String userName, String password) throws UserExceptions {

        User user = null;  
        try {  
            List<Users> usersList = super.getEm().createQuery("SELECT u from Users u where u.username = ?1 and u.password = ?2")
              .setParameter(1, userName)
              .setParameter(2, password)
              .getResultList();
            
            if( Objects.nonNull(usersList)){
                for( Users u : usersList){
                    user  = UserBuild.build(u);
                    return user;
                }
            }
            
        } catch (PersistenceException pe) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, pe);
            throw new UserExceptions(UserExceptionsMensajes.ERROR_CONSULTADO_USUARIO, "0001", pe.getMessage());
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new UserExceptions(UserExceptionsMensajes.ERROR_CONSULTADO_USUARIO, "0001", e.getMessage());
        }
        return user;
    }

    public User FindUserGeneral(HashMap<String, String> parameters) throws UserExceptions {
        User user = null;
        return user;
    }
    
    public Users findByEmail(String email){
         try {  
            List<Users> usersList = super.getEm().createQuery("SELECT u from Users u where u.email = ?1 ")
              .setParameter(1, email)
              .getResultList();
            if( Objects.nonNull(usersList)){
                for( Users u : usersList){
                    return u;
                }
            }
            
        } catch (PersistenceException pe) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, pe);
            throw new UserExceptions(UserExceptionsMensajes.ERROR_CONSULTANDO_USUARIO_BY_EMAIL , "0001", pe.getMessage());
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new UserExceptions(UserExceptionsMensajes.ERROR_CONSULTADO_USUARIO, "0001", e.getMessage());
        }
         return null;
    }

    @Override
    public List<Users> getAll() throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void save(Users u) throws GenericException {
          try {     
                super.getEm().persist(u);         
        } catch (PersistenceException pe) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, pe);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_GUARDANDO_USUARIOS, "0001", pe.getMessage());
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_GUARDANDO_USUARIOS, "0001", e.getMessage());
        }
    }

    @Override
    public void edith(Users p) throws GenericException {
         try {     
                super.getEm().merge(p);         
        } catch (PersistenceException pe) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, pe);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_GUARDANDO_USUARIOS, "0001", pe.getMessage());
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_GUARDANDO_USUARIOS, "0001", e.getMessage());
        }
    }

       @Override
    public Users find(Users p) throws GenericException {
           try {     
                Users u = super.getEm().find(Users.class, p.getId());
                return u;
        } catch (PersistenceException pe) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, pe);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_GUARDANDO_USUARIOS, "0001", pe.getMessage());
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_GUARDANDO_USUARIOS, "0001", e.getMessage());
        }
    }
    
    
    

    @Override
    public List<Users> findAll(Users p) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Users p) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void desactivate(Users usuario) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}
