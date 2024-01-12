
package co.system.out.serverchat.business;

import co.system.out.chatsocket.general.models.Solicitud;
import co.system.out.serverchat.dao.IDao;
import co.system.out.serverchat.entitys.Solicitudes;
import co.system.out.serverchat.entitys.Users;
import com.gosystem.commons.exceptions.BasicExeption;
import com.gosystem.commons.exceptions.GenericException;
import java.util.List;

public interface ISocilitudesBusiness  extends  IDao<Solicitudes>{
    
    void crear(Long idUSerAnfitrion , Users user) throws BasicExeption;
    
    void acceptar(Long idSolicitud ) throws BasicExeption;
    
    void rechazar(Long idSolicitud ) throws BasicExeption;
    
    List<Solicitud> getAllByUserId (Long idUSer) throws BasicExeption;
    
    Solicitudes getById(Long id) throws BasicExeption ;
    
}
