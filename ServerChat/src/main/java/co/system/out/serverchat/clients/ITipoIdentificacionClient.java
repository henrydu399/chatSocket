package co.system.out.serverchat.clients;

import java.util.List;

import com.gosystem.commons.adminUsers.dto.TipoIdentificacionDTO;
import com.gosystem.commons.exceptions.HomeException;

public interface ITipoIdentificacionClient {
	
	List<TipoIdentificacionDTO> getAll() throws HomeException;

}
