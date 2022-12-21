package cu.edu.cujae.pweb.bean.managebeans;

import cu.edu.cujae.pweb.bean.UserBean;
import cu.edu.cujae.pweb.dto.RoleDto;
import cu.edu.cujae.pweb.dto.UserDto;
import cu.edu.cujae.pweb.service.RoleService;
import cu.edu.cujae.pweb.service.UserService;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageUserBean {

	private UserDto userDto;
	private UserDto selectedUser;
	private List<UserDto> users;
	private Integer[] selectedRoles;

	private List<RoleDto> roles;

	private UserBean currentUser;

	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	public ManageUserBean() {}


	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
		this.selectedUser = new UserDto();
		this.selectedRoles = null;
	}

	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
		List<RoleDto> roles = this.selectedUser.getRoles();
		this.selectedRoles =
				roles.stream().map(RoleDto::getCode).toArray(Integer[]::new);
	}

	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveUser() {
		String msg = "";
		if (this.selectedUser.getCode() == 0) {
			List<RoleDto> rolesToAdd = new ArrayList<RoleDto>();
			for (int i = 0; i < this.selectedRoles.length; i++) {
				rolesToAdd.add(roleService.getById(Long.valueOf(selectedRoles[i])));
			}
			selectedUser.setRoles(rolesToAdd);

			// check if the email already exists
			if(userService.getByEmail(selectedUser.getEmail()) != null) {
				msg = "El usuario ya existe";
				JsfUtils.addMessageFromBundle(
						null,
						FacesMessage.SEVERITY_INFO,
						"message_user_email_exists"
				);
				return;
			}

			// check if the username already exists
			if(userService.getByUsername(selectedUser.getUsername()) != null) {
				msg = "El usuario ya existe";
				JsfUtils.addMessageFromBundle(
						null,
						FacesMessage.SEVERITY_INFO,
						"message_user_username_exists"
				);
				return;
			}

			//if not, save the user
			if(msg.isEmpty()){
				msg = userService.create(selectedUser);
				users = userService.getAll();
				JsfUtils.addMessageFromBundle(
						null,
						FacesMessage.SEVERITY_INFO,
						"message_user_added"
				);
			}

		} else {

			List<RoleDto> rolesToAdd = new ArrayList<RoleDto>();
			for (int i = 0; i < this.selectedRoles.length; i++) {
				rolesToAdd.add(roleService.getById(Long.valueOf(selectedRoles[i])));
			}
			selectedUser.setRoles(rolesToAdd);


			if(msg.isEmpty()){
				msg = userService.update(selectedUser);
				users = userService.getAll();
				JsfUtils.addMessageFromBundle(
						null,
						FacesMessage.SEVERITY_INFO,
						"message_user_edited"
				);
			}
		}
		users = userService.getAll();
		PrimeFaces.current().executeScript("PF('manageUserDialog').hide()"); //Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
		PrimeFaces.current().ajax().update("form:dt-users"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
	}

	public void onCancel(){
		PrimeFaces.current().ajax().update("form:dt-users");
		this.selectedUser = null;
	}

	//Permite eliminar un usuario
	public void deleteUser(String currentUser) {
		UserDto current = userService.getByUsername(currentUser);
		if(current.getUsername().equals(this.selectedUser.getUsername())){
			JsfUtils.addMessageFromBundle(
					null,
					FacesMessage.SEVERITY_INFO,
					"message_user_you_cant_delete"
			);
			return;
		}
		else{
			try {
				userService.delete((long) selectedUser.getCode());
				this.selectedUser = null;
				users = userService.getAll();
				JsfUtils.addMessageFromBundle(
						null,
						FacesMessage.SEVERITY_INFO,
						"message_user_deleted"
				);
				PrimeFaces.current().ajax().update("form:dt-users"); // Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
			} catch (Exception e) {
				JsfUtils.addMessageFromBundle(
						null,
						FacesMessage.SEVERITY_ERROR,
						"message_error"
				);
			}
		}

		users = userService.getAll();
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public UserDto getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserDto selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<UserDto> getUsers() {
		users = userService.getAll();
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}

	public Integer[] getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(Integer[] selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public List<RoleDto> getRoles() {
		roles = roleService.getAll();
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}
}