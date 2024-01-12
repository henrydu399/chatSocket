package co.system.out.serverchat.clients;

import java.util.List;

import com.gosystem.commons.adminUsers.dto.PrivilegiosDTO;
import com.gosystem.commons.exceptions.AdministradorUserException;


public interface IPrivilegioClientService extends  IDtoDao<PrivilegiosDTO> {

	List<PrivilegiosDTO> getAllBySistemName(String sistemName) throws AdministradorUserException;

}
