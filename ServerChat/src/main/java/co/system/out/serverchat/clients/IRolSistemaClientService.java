package co.system.out.serverchat.clients;

import java.util.List;

import com.gosystem.commons.adminUsers.dto.PrivilegiosDTO;
import com.gosystem.commons.adminUsers.dto.RolesSistemaDTO;
import com.gosystem.commons.exceptions.AdministradorUserException;



public interface IRolSistemaClientService   extends  IDtoDao<RolesSistemaDTO> {
	
	 public List<RolesSistemaDTO> findBySystema(String sistemaName)throws AdministradorUserException;

}
