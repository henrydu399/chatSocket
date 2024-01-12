
package co.system.out.serverchat.dao;

import co.system.out.chatsocket.general.models.User;
import co.system.out.serverchat.entitys.Contactos;
import co.system.out.serverchat.entitys.Solicitudes;
import co.system.out.serverchat.exceptions.UserExceptions;
import com.google.gson.Gson;
import com.gosystem.commons.exceptions.GenericException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;


public class SolicitudesDao extends Dao implements IDao<Solicitudes>  {

    public SolicitudesDao(EntityManager em) {
        super(em);
    }
    
    
    public Solicitudes getById(Long id) throws GenericException {
        Solicitudes s = null;
        s = super.getEm().find(Solicitudes.class, id);
        return s;
    }
    
    public boolean existSolicitud(Long idUserAfrition , Long idUSer){
        try {
            List<Solicitudes> list = super.getEm().createQuery("SELECT s from Solicitudes s where s.idUser.id = ?1  and s.idUserSolicitud.id = ?2")
                    .setParameter(1, idUserAfrition)
                    .setParameter(2, idUSer)
                    .getResultList();

            if (Objects.nonNull(list) && list.size() > 0 ) {
                return true;
            }

        } catch (PersistenceException pe) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, pe);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_CONSULTANDO_CONTACTOS, "0001", pe.getMessage());
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_CONSULTANDO_CONTACTOS, "0001", e.getMessage());
        }
             return false;
    }
    
 
    public List<Solicitudes> getAllByUserId(Long idUser) throws GenericException {
     
           try {
            List<Solicitudes> list = super.getEm().createQuery("SELECT s from Solicitudes s where s.idUser.id = ?1 ")
                    .setParameter(1, idUser)
                    .getResultList();
            return list;

        } catch (PersistenceException pe) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, pe);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_CONSULTANDO_CONTACTOS, "0001", pe.getMessage());
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_CONSULTANDO_CONTACTOS, "0001", e.getMessage());
        }
    }

    @Override
    public List<Solicitudes> getAll() throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void save(Solicitudes u) throws GenericException {
          try {
              super.getEm().getTransaction().begin();
              super.getEm().persist(u);
              super.getEm().flush();
              super.getEm().getTransaction().commit();
        } catch (PersistenceException pe) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, pe);
            throw new GenericException("Error guardando Solicitud de contacto");
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
              throw new GenericException("Error guardando Solicitud de contacto");
        }
    }

    @Override
    public void edith(Solicitudes p) throws GenericException {
              try {
            super.getEm().merge(p);
        } catch (PersistenceException pe) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, pe);
            throw new GenericException("Error editando Solicitud de contacto");
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
              throw new GenericException("Error editando Solicitud de contacto");
        }
    }

    @Override
    public Solicitudes find(Solicitudes p) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Solicitudes> findAll(Solicitudes p) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Solicitudes p) throws GenericException {
    try {
            super.getEm().remove(p);
        } catch (PersistenceException pe) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, pe);
            throw new GenericException("Error eliminando Solicitud de contacto");
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
              throw new GenericException("Error eliminando Solicitud de contacto");
        }      
    }

    @Override
    public void desactivate(Solicitudes usuario) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
