package co.system.out.serverchat.clients;

import com.gosystem.commons.emailapi.dto.EmailDto;
import com.gosystem.commons.exceptions.HomeException;

public interface IEmailClientService {

void send (EmailDto dto) throws HomeException; 

}
