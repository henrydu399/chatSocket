package co.system.out.serverchat.business;

import co.system.out.serverchat.exceptions.UserExceptions;
import co.system.out.chatsocket.general.models.*;
import co.system.out.serverchat.entitys.Contactos;
import java.util.List;

public interface IContactosBusiness {

    List<User> getContactosByUser(Long idUser) throws UserExceptions;

    void addContacto(long idUser, String email) throws UserExceptions;

    boolean existeContactoCreado(Long idUSerPrincipal, String emailContacto);

    Contactos getContactoById(Long id);

}
