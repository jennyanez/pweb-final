package cu.edu.cujae.pweb.bean;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.CopyDto;
import cu.edu.cujae.pweb.service.CopyService;
import cu.edu.cujae.pweb.utils.JsfUtils;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageCopyBean {
	
	private CopyDto copy;
	private CopyDto selectedCopy;
	private List<CopyDto> copies;
	
	
	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private CopyService copyService;
	
	
	public ManageCopyBean() {
		
	}
	
	
	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase. 
	@PostConstruct
    public void init() {
	    copies = copies == null ? copyService.getCopies() : copies;
    }
	
	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
        this.setSelectedCopy(new CopyDto());
    }
	
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
		
	}
	
	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveCopy() {
		if (this.selectedCopy.getCopyId() == null) {
            this.selectedCopy.setCopyId(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            this.selectedCopy.setNewRecord(true);
            this.copies.add(this.selectedCopy);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_copy_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_copy_edited");
        }
        PrimeFaces.current().executeScript("PF('manageCopyDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-copy");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form	
	
    }

	//Permite eliminar un usuario
    public void deleteCopy() {
    	try {
    		this.copies.remove(this.selectedCopy);
            this.selectedCopy = null;
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_copy_removed");
            PrimeFaces.current().ajax().update("form:dt-copy");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
    }
    
    
    
/******************************   GETTERS AND SETTERS   *****************************/
	/**
	 * @return the copy
	 */
	public CopyDto getCopy() {
		return copy;
	}

	/**
	 * @param copy the copy to set
	 */
	public void setCopy(CopyDto copy) {
		this.copy = copy;
	}

	/**
	 * @return the selectedCopy
	 */
	public CopyDto getSelectedCopy() {
		return selectedCopy;
	}

	/**
	 * @param selectedCopy the selectedCopy to set
	 */
	public void setSelectedCopy(CopyDto selectedCopy) {
		this.selectedCopy = selectedCopy;
	}

	/**
	 * @return the copies
	 */
	public List<CopyDto> getCopies() {
		return copies;
	}

	/**
	 * @param copies the copies to set
	 */
	public void setCopies(List<CopyDto> copies) {
		this.copies = copies;
	}

	/**
	 * @return the books
	 */
	



}
