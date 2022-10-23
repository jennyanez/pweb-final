package cu.edu.cujae.pweb.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

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
	private List<BookDto> books;
	
	@Autowired
	private BookService bookService;
	
	
	public ManageBookBean() {
		
	}
	
	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase. 
	@PostConstruct
    public void init() {
	    books = books == null ? bookService.getBooks() : books;
    }
	
	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
        this.selectedBook = new BookDto();
    }
	
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
		
	}
	
	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveBook() {
		
	}

	//Permite eliminar un usuario
    public void deleteBook() {
    	try {
    		this.books.remove(this.selectedBook);
            this.selectedBook = null;
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_book_removed");
            PrimeFaces.current().ajax().update("form:dt-books");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
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


}
