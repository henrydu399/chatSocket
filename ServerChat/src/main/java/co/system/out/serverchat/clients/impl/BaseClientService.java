package co.system.out.serverchat.clients.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.gosystem.commons.constants.ErrorConstantes;
import com.gosystem.commons.enums.EntityEnum;
import com.gosystem.commons.enums.LayerEnum;
import com.gosystem.commons.enums.MethodsEnum;
import com.gosystem.commons.exceptions.HomeException;
import com.gosystem.commons.utils.UtilsLogs;



public class BaseClientService {
	
	private RestTemplate restTemplate  ;
	private Logger logger;
	
	public BaseClientService(String className) {
		super();
		this.restTemplate = new RestTemplate();
		logger = UtilsLogs.getLogger(ClientParametrizacionServiceImpl.class.getName());
	}
	
	
	
	public boolean isSuccess(ResponseEntity<?>  response) {
		 if(     response.getStatusCode() == HttpStatus.ACCEPTED ||  
				 response.getStatusCode() == HttpStatus.CREATED ||
				 response.getStatusCode() == HttpStatus.OK ) {
			 return true;
		 }
		 return false;
	}
	
	public URI isUrlValid (String urlFull)  throws HomeException{
		try {
			  return  new URI(urlFull);
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
				throw new HomeException( EntityEnum.USUARIO, MethodsEnum.GETALL, LayerEnum.CLIENT ,ErrorConstantes.ERROR_CON_URL + urlFull );			
			}
	}

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
	
	
	
	
	
	
	

}
