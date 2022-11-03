package cu.edu.cujae.pweb.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.DefaulterUserDto;
import cu.edu.cujae.pweb.service.DefaulterUserService;
import cu.edu.cujae.pweb.utils.JsfUtils;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageDefaulterUserBean {
	
	private DefaulterUserDto defUser;
	private DefaulterUserDto selectedUser;
	private List<DefaulterUserDto> defUsers;
	
	
	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private DefaulterUserService defUserService;
	
	public ManageDefaulterUserBean() {
		
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
	public DefaulterUserDto getDefUser() {
		return defUser;
	}

	public void setDefUser(DefaulterUserDto defUser) {
		this.defUser = defUser;
	}

	public DefaulterUserDto getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(DefaulterUserDto selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<DefaulterUserDto> getDefUsers() {
		return defUsers;
	}

	public void setDefUsers(List<DefaulterUserDto> defUsers) {
		this.defUsers = defUsers;
	}


}
