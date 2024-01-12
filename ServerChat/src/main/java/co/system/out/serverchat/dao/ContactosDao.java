package co.system.out.serverchat.dao;

import co.system.out.serverchat.exceptions.UserExceptions;
import co.system.out.chatsocket.general.models.*;

import co.system.out.serverchat.entitys.Contactos;
import co.system.out.serverchat.entitys.Users;

import co.system.out.serverchat.util.GSonUtils;
import com.google.gson.Gson;
import com.gosystem.commons.exceptions.GenericException;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

public class ContactosDao extends Dao implements IDao<Contactos> {

    public ContactosDao(EntityManager em) {
        super(em);
    }

    public Contactos getContactoById(Long id) {
        try {
            Contactos c = (Contactos) super.getEm().createQuery("SELECT c from Contactos c where c.userId = ?1 ")
                    .setParameter(1, id)
                    .getSingleResult();
        } catch (PersistenceException pe) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, pe);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_CONSULTANDO_CONTACTOS, "0001", pe.getMessage());
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_CONSULTANDO_CONTACTOS, "0001", e.getMessage());
        }
        return null;
    }

    public List<User> getContactosByUser(long idUser) {

        List<User> out = null;
        User user = null;
        try {
            List<Contactos> list = super.getEm().createQuery("SELECT c from Contactos c where c.userId = ?1 ")
                    .setParameter(1, idUser)
                    .getResultList();

            if (Objects.nonNull(list)) {
                out = new ArrayList<User>();
                for (Contactos c : list) {
                    Gson gson = new Gson();
                    String contactosString = new String(c.getContactos()); 
                    User[] array = gson.fromJson(contactosString, User[].class);
                    out = Arrays.asList(array);
                    return out;
                }
            }

        } catch (PersistenceException pe) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, pe);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_CONSULTANDO_CONTACTOS, "0001", pe.getMessage());
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_CONSULTANDO_CONTACTOS, "0001", e.getMessage());
        }
        return out;

    }

    /**
     * Crean un nuevo contacto u = Usuario a agregar, idUSer = id del usuario
     * principal
     *
     * @param u
     * @param idUser
     */
    public void addContacto(User userContacto,  Users userPrincipal) {

        List<User> listContactos = this.getContactosByUser(userPrincipal.getId());

        boolean isnew = false;

        if (Objects.nonNull(listContactos) &&  listContactos.size() > 0) {
            listContactos.add(userContacto);
        } else {
            isnew = true;
            listContactos = new ArrayList<User>();
            listContactos.add(userContacto);
        }



        String contactosJson = GSonUtils.serialize(listContactos);
        if (isnew) {
            Contactos c = new Contactos();
            c.setUserId(userPrincipal.getId());
            c.setContactos(contactosJson.getBytes());
            c.setUsers(userPrincipal);
            this.save(c);
        } else {
            Contactos contactoByFind = new Contactos();
            contactoByFind.setUserId(userPrincipal.getId());
            Contactos contactosUser = this.find(contactoByFind); 
            contactosUser.setContactos(contactosJson.getBytes());
            this.edith(contactosUser);
        }

    }

    public void saveUser(User u) {

    }

    @Override
    public void save(Contactos u) throws GenericException {
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
    public void edith(Contactos p) throws GenericException {
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
    public Contactos find(Contactos p) throws GenericException {
        try {
            Contactos c = super.getEm().find(Contactos.class, p.getUserId());
            return c;
        } catch (PersistenceException pe) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, pe);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_GUARDANDO_USUARIOS, "0001", pe.getMessage());
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_GUARDANDO_USUARIOS, "0001", e.getMessage());
        }
    }

    @Override
    public List<Contactos> findAll(Contactos p) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Contactos p) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void desactivate(Contactos usuario) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Contactos> getAll() throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
