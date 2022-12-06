package cu.edu.cujae.pweb.bean.managebeans;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import cu.edu.cujae.pweb.dto.MatterDto;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.BookDto;
import cu.edu.cujae.pweb.service.BookService;
import cu.edu.cujae.pweb.utils.JsfUtils;

@Component 
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageBookBean {
	
	private BookDto bookDto;
	private BookDto selectedBook;
	private MatterDto selectedMatter;
	private List<BookDto> books;
	private List<MatterDto> matters;
	
	@Autowired
	private BookService bookService;
	
	
	public ManageBookBean() {
		
	}
	
	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase. 
	@PostConstruct
    public void init() {
		books = books == null ? bookService.getAll() : books;
    }
	
	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
        this.selectedBook = new BookDto();
    }
	
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
		//codigo aqui
	}
	
	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveBook() {
		if (this.selectedBook.getBookId() == null) {
            this.selectedBook.setBookId(1L);

            this.books.add(this.selectedBook);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_book_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_book_edited");
        }

        PrimeFaces.current().executeScript("PF('manageBookDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-book");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
	}

	//Permite eliminar un usuario
    public void deleteBook() {
    	try {
    		this.books.remove(this.selectedBook);
            this.selectedBook = null;
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_book_deleted");
            PrimeFaces.current().ajax().update("form:dt-book");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
        
    }

	public BookDto getBookDto() {
		return this.bookDto;
	}

	public void setBookDto(BookDto bookDto) {
		this.bookDto = bookDto;
	}

	public BookDto getSelectedBook() {
		return this.selectedBook;
	}

	public void setSelectedBook(BookDto selectedLoan) {
		this.selectedBook = selectedLoan;
	}

	public List<BookDto> getBooks() {
		return this.books;
	}

	public void setBooks(List<BookDto> books) {
		this.books = books;
	}

	public MatterDto getSelectedMatter() {
		return selectedMatter;
	}

	public void setSelectedMatter(MatterDto selectedMatter) {
		this.selectedMatter = selectedMatter;
	}

	public List<MatterDto> getMatters() {
		return matters;
	}

	public void setMatters(List<MatterDto> matters) {
		this.matters = matters;
	}
}
