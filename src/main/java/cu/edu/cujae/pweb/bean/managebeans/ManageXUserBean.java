package cu.edu.cujae.pweb.bean.managebeans;

import cu.edu.cujae.pweb.dto.XRoleDto;
import cu.edu.cujae.pweb.dto.XUserDto;
import cu.edu.cujae.pweb.service.RoleService;
import cu.edu.cujae.pweb.service.UserService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.List;


@Component
@ManagedBean
@ViewScoped 
public class ManageXUserBean {
	
	private XUserDto userDto;
	private XUserDto selectedUser;
	private List<XUserDto> users;
	private XRoleDto selectedRol;
	private List<XRoleDto> roles;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	public ManageXUserBean() {
		
	}
	
	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase. 
	@PostConstruct
    public void init() {
		users = userService.getAll();
		roles = roleService.getAll();
    }
	
	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
        this.selectedUser = new XUserDto();
        this.selectedRol = null;
    }
	
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit(XUserDto user) {
		this.selectedUser = user;
		this.selectedRol = this.selectedUser.getRol();
	}
	
	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveUser() {
        if (this.selectedUser.getUsername() == null) {
			this.selectedUser.setRol(this.selectedRol);
            userService.create(this.selectedUser);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
        	//update user
			this.selectedUser.setRol(this.selectedRol);
            userService.update(this.selectedUser);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_edited");
        }
        //load datatable again with new values
        users = userService.getAll();
        
        PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-users");
    }

	//Permite eliminar un usuario
    public void deleteUser() {
   		try {
    		//delete user
    		userService.deleteByUsername(this.selectedUser.getUsername());
            this.selectedUser = null;

            //load datatable again with new values
            users = userService.getAll();
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_deleted");
            PrimeFaces.current().ajax().update("form:dt-users");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
    }

	/****** GETTERS AND SETTERS ********/
	public XUserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(XUserDto userDto) {
		this.userDto = userDto;
	}

	public XUserDto getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(XUserDto selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<XUserDto> getUsers() {
		
		return users;
	}

	public void setUsers(List<XUserDto> users) {
		this.users = users;
	}

	public List<XRoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<XRoleDto> roles) {
		this.roles = roles;
	}

	public XRoleDto getSelectedRol() {
		return selectedRol;
	}

	public void setSelectedRol(XRoleDto selectedRol) {
		this.selectedRol = selectedRol;
	}
}