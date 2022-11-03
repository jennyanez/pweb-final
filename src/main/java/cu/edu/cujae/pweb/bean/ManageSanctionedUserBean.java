package cu.edu.cujae.pweb.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.SanctionedUserDto;
import cu.edu.cujae.pweb.service.SanctionedUserService;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageSanctionedUserBean {

	private SanctionedUserDto sanctUser;
	private SanctionedUserDto selectedUser;
	private List<SanctionedUserDto> sanctUsers;


	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private SanctionedUserService sanctUserService;



	public ManageSanctionedUserBean() {

	}

	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase. 
	@PostConstruct
	public void init() {
		sanctUsers = sanctUsers == null ? sanctUserService.getSanctUsers() : sanctUsers;
	}

	/********************** GETTERS AND SETTERS ***********************/   
	public SanctionedUserDto getSanctUser() {
		return sanctUser;
	}

	public void setSanctUser(SanctionedUserDto sanctUser) {
		this.sanctUser = sanctUser;
	}

	public List<SanctionedUserDto> getSanctUsers() {
		return sanctUsers;
	}

	public void setSanctUsers(List<SanctionedUserDto> sanctUsers) {
		this.sanctUsers = sanctUsers;
	}

	public void setSelectedUser(SanctionedUserDto selectedUser) {
		this.selectedUser = selectedUser;
	}

	public SanctionedUserDto getSelectedUser() {
		return selectedUser;
	}
}
