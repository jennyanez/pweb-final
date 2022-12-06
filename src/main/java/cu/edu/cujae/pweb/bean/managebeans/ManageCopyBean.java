package cu.edu.cujae.pweb.bean.managebeans;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.BookDto;
import cu.edu.cujae.pweb.dto.CopyDto;
import cu.edu.cujae.pweb.service.CopyService;
import cu.edu.cujae.pweb.utils.JsfUtils;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageCopyBean {
	
	private CopyDto selectedCopy;
	private BookDto selectedBook;
	private List<CopyDto> copies;
	private List<BookDto> books;
	
	
	
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
	    copies = copyService.getAll();
	    books = copyService.getAllBook();	    
	}
	
	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
        this.selectedCopy= new CopyDto();
        this.selectedBook = null;
    }

	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit(CopyDto copy) {
		this.selectedCopy = copy;
		this.selectedBook = copy.getBook();
	}

	
	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveCopy() {
		if (this.selectedCopy.getCopyId() == null){
			copyService.create(this.selectedCopy);
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_copy_added");
		}else{
			copyService.update(this.selectedCopy);
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_copy_edited");
		}
		copies = copyService.getAll();
		PrimeFaces.current().executeScript("PF('manageCopyDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-copy");

    }

	//Permite eliminar un usuario
    public void deleteCopy() {
    	try {
			copyService.delete(this.selectedCopy.getCopyId());
			this.selectedCopy = null;
			copies = copyService.getAll();
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_copy_removed");
			PrimeFaces.current().ajax().update("form:dt-copy");
		}catch (Exception e){
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
    }


	public CopyDto getSelectedCopy() {
		return selectedCopy;
	}


	public void setSelectedCopy(CopyDto selectedCopy) {
		this.selectedCopy = selectedCopy;
	}


	public List<CopyDto> getCopies() {
		return copies;
	}


	public void setCopies(List<CopyDto> copies) {
		this.copies = copies;
	}
	public BookDto getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(BookDto selectedBook) {
		this.selectedBook = selectedBook;
	}


	public List<BookDto> getBooks() {
		return books;
	}


	public void setBooks(List<BookDto> books) {
		this.books = books;
	}


    
    
    
/******************************   GETTERS AND SETTERS   *****************************/
	

}
