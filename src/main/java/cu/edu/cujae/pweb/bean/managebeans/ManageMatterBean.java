package cu.edu.cujae.pweb.bean.managebeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import cu.edu.cujae.pweb.service.MatterService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.MatterDto;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageMatterBean {

	private MatterDto matter;
	private MatterDto selectedMatter;
	private List<MatterDto> matters;


	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private MatterService matterService;


	public ManageMatterBean() {

	}

	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase. 

	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
		this.selectedMatter = new MatterDto();
	}
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit(MatterDto matter) {
		this.selectedMatter = matter;
	}

	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveMatter() {
		      if (this.selectedMatter.getMatterId() == null) {
				  matterService.create(this.selectedMatter);
				  JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_matter_added");
            }else {
				  matterService.update(this.selectedMatter);
            	  JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_matter_edited");
        }
		matters = matterService.getAll();
        PrimeFaces.current().executeScript("PF('manageMatterDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-matter");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form

	}

	//Permite eliminar un usuario
	public void deleteMatter() {
		 	try {
    			matterService.delete(this.selectedMatter.getMatterId());
				this.selectedMatter = null;
				matters = matterService.getAll()
;            	JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_matter_removed");
            	PrimeFaces.current().ajax().update("form:dt-matter");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}

	}


	/********************** GETTERS AND SETTERS ***********************/
	public MatterDto getMatter() {
		return matter;
	}

	public void setMatter(MatterDto matter) {
		this.matter = matter;
	}

	public MatterDto getSelectedMatter() {
		return selectedMatter;
	}

	public void setSelectedMatter(MatterDto selectedMatter) {
		this.selectedMatter = selectedMatter;
	}

	public List<MatterDto> getMatters() {
		matters = matterService.getAll();
		return matters;
	}

	public void setMatters(List<MatterDto> matters) {
		this.matters = matters;
	}

}
