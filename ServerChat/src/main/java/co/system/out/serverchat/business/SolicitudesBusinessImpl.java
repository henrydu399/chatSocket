
package co.system.out.serverchat.business;


import co.system.out.chatsocket.general.models.Solicitud;
import co.system.out.serverchat.dao.IDao;
import co.system.out.serverchat.dao.SolicitudesDao;
import co.system.out.serverchat.entitys.Solicitudes;

import co.system.out.serverchat.entitys.Users;
import com.gosystem.commons.exceptions.BasicExeption;
import com.gosystem.commons.exceptions.GenericException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;


public class SolicitudesBusinessImpl  implements  ISocilitudesBusiness{
    
    private SolicitudesDao solicitudesDao;    
    private IUserBusiness userBusiness;
    private IContactosBusiness contactosBusiness;

    public SolicitudesBusinessImpl(EntityManager _em , IUserBusiness _userBusiness, IContactosBusiness _contactosBusiness) {
        this.solicitudesDao = new SolicitudesDao(_em);
        this.userBusiness = _userBusiness;
        this.contactosBusiness = _contactosBusiness;
    }
     

    public Solicitudes getById(Long id) throws GenericException {
        return this.solicitudesDao.getById(id);
    }

     
   @Override
   public List<Solicitud> getAllByUserId (Long idUSer) throws BasicExeption{
       List<Solicitud> out = null ;
       List<Solicitudes> list = this.solicitudesDao.getAllByUserId(idUSer);
       if( Objects.nonNull(list) && list.size() > 0){
           out =  new ArrayList<Solicitud>();
           for ( Solicitudes s : list){
               Solicitud solicitud =  new Solicitud(s.getId(), s.getNombre(), s.getApellido(),s.getEmail());
               out.add(solicitud);
           }
       }
       return out;    
   }
    

    @Override
    public void crear(Long idUSerAnfitrion, Users user) throws BasicExeption {
        boolean existeSolicitud = this.solicitudesDao.existSolicitud(idUSerAnfitrion, user.getId());
        if( !existeSolicitud){
            
            boolean existeContactoCreado = this.contactosBusiness.existeContactoCreado(idUSerAnfitrion, user.getEmail());
            if(!existeContactoCreado ){
                    Users userAfitrion = this.userBusiness.findById(idUSerAnfitrion);
                    Solicitudes s =  new Solicitudes();
                    s.setIdUser(userAfitrion);
                    s.setIdUserSolicitud(user);
                    s.setNombre(user.getNombre());
                    s.setApellido(user.getApellidos());
                    s.setEmail(user.getEmail());
                     this.solicitudesDao.save(s);
            }else{
                throw new BasicExeption("Ya tienes este contacto en tu lista",null);
            }
        }else{
             throw new BasicExeption("Existe una solicitud prendiente",null);
        }
       
    }
    
    

    @Override
    public void acceptar(Long idSolicitud) throws BasicExeption {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void rechazar(Long idSolicitud) throws BasicExeption {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getAll() throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void save(Solicitudes u) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void edith(Solicitudes p) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        this.solicitudesDao.delete(p);
    }

    @Override
    public void desactivate(Solicitudes usuario) throws GenericException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
   
    
}
