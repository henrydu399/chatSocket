/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.system.out.serverchat.clients.streams;

import co.system.out.serverchat.clients.IAdministracionClientUsers;
import com.gosystem.commons.adminUsers.dto.RolesSistemaDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author henry
 */
public class RolUsuariosStream {
    
    	@Value("${application.name}")
	private String aplicationName;
	
	@Autowired
	private IAdministracionClientUsers clientAdministracionUsers;
	
	
	public  RolesSistemaDTO findBySistemName(String name) {
		List <RolesSistemaDTO> list =  this.clientAdministracionUsers.getRolesBySystema(aplicationName);	
		RolesSistemaDTO out = list.stream()
				  .filter(customer -> name.equals(customer.getId().getNombreRol()))
				  .findAny()
				  .orElse(null);
		return out;
	}
    
}
