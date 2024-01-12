package co.system.out.serverchat.clients;

import java.util.List;

import com.gosystem.commons.adminUsers.dto.RolesUsuarioDTO;
import com.gosystem.commons.adminUsers.dto.UsuarioDTO;
import com.gosystem.commons.exceptions.AdministradorUserException;
import com.gosystem.commons.exceptions.HomeException;




public interface IRolesUsuarioClientService extends IDtoDao<RolesUsuarioDTO>{
	
	 List<RolesUsuarioDTO> findByUsuario(UsuarioDTO usuario) throws HomeException ;
	 List<RolesUsuarioDTO> getAllSistemaByName(String sistemaName) throws HomeException ;
	 RolesUsuarioDTO findById(RolesUsuarioDTO p) throws HomeException ;

}
