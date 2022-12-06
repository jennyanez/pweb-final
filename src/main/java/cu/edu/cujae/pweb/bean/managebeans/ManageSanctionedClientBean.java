package cu.edu.cujae.pweb.bean.managebeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.SanctionedClientDto;
import cu.edu.cujae.pweb.service.SanctionedClientService;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageSanctionedClientBean {

	private SanctionedClientDto sanctUser;
	private SanctionedClientDto selectedUser;
	private List<SanctionedClientDto> sanctUsers;


	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private SanctionedClientService sanctUserService;



	public ManageSanctionedClientBean() {

	}

	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase. 
	@PostConstruct
	public void init() {
		sanctUsers = sanctUsers == null ? sanctUserService.getSanctUsers() : sanctUsers;
	}

	/********************** GETTERS AND SETTERS ***********************/   
	public SanctionedClientDto getSanctUser() {
		return sanctUser;
	}

	public void setSanctUser(SanctionedClientDto sanctUser) {
		this.sanctUser = sanctUser;
	}

	public List<SanctionedClientDto> getSanctUsers() {
		return sanctUsers;
	}

	public void setSanctUsers(List<SanctionedClientDto> sanctUsers) {
		this.sanctUsers = sanctUsers;
	}

	public void setSelectedUser(SanctionedClientDto selectedUser) {
		this.selectedUser = selectedUser;
	}

	public SanctionedClientDto getSelectedUser() {
		return selectedUser;
	}
}
