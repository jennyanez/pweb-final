package cu.edu.cujae.pweb.bean.managebeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import cu.edu.cujae.pweb.dto.DefaulterClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.service.DefaulterClientService;
import cu.edu.cujae.pweb.utils.JsfUtils;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageDefaulterClientBean {
	
	private DefaulterClientDto defUser;
	private DefaulterClientDto selectedUser;
	private List<DefaulterClientDto> defUsers;
	
	
	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private DefaulterClientService defUserService;
	
	public ManageDefaulterClientBean() {
		
	}
	
	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase. 
	@PostConstruct
    public void init() {
	    defUsers = defUsers == null ? defUserService.getDefUsers() : defUsers;
    }
	
	//Permite eliminar un usuario
    public void deleteUser() {
    	try {
    		this.defUsers.remove(this.selectedUser);
            this.selectedUser = null;
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_def_user_removed");
         //   PrimeFaces.current().ajax().update("form:dt-defaulter");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}   
    }
    
    
/********************** GETTERS AND SETTERS ***********************/
	public DefaulterClientDto getDefUser() {
		return defUser;
	}

	public void setDefUser(DefaulterClientDto defUser) {
		this.defUser = defUser;
	}

	public DefaulterClientDto getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(DefaulterClientDto selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<DefaulterClientDto> getDefUsers() {
		return defUsers;
	}

	public void setDefUsers(List<DefaulterClientDto> defUsers) {
		this.defUsers = defUsers;
	}


}
