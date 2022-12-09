package cu.edu.cujae.pweb.bean.managebeans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import cu.edu.cujae.pweb.dto.AuthorDto;
import cu.edu.cujae.pweb.dto.MatterDto;
import cu.edu.cujae.pweb.service.AuthorService;
import cu.edu.cujae.pweb.service.MatterService;
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
	private Long selectedMatter;
	private List<BookDto> books;
	private List<MatterDto> matters;
	private List<AuthorDto> authors;
	private Long[] selectedAuthors;
	
	@Autowired
	private BookService bookService;
	@Autowired
	private MatterService matterService;
	@Autowired
	private AuthorService authorService;
	
	
	public ManageBookBean() {
		
	}
	
	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase. 
	@PostConstruct
    public void init() {
		books = bookService.getAll();
		matters = matterService.getAll();
		authors = authorService.getAll();
    }
	
	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
		this.selectedBook = new BookDto();
		this.selectedMatter = null;
		this.selectedAuthors = null;
    }
	
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
		MatterDto matter = this.selectedBook.getMatter();
		this.selectedMatter = matter.getMatterId();

		List<AuthorDto> authorDtos = this.selectedBook.getAuthors();
		this.selectedAuthors = authorDtos.stream().map(AuthorDto::getAuthorId).toArray(Long[]::new);
	}

	
	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveBook() {
		if (this.selectedBook.getBookId() == null) {
            bookDto = selectedBook;
			bookDto.setMatter(matterService.getById(selectedMatter));
			List<AuthorDto> authorDtos = new ArrayList<>();
			for (Long selectedAuthor : selectedAuthors) {
				authorDtos.add(authorService.getById(selectedAuthor));
			}
			bookDto.setAuthors(authorDtos);
			bookService.create(bookDto);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_book_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
			/////codigo para updatear
			List<AuthorDto> authorDtos = new ArrayList<>();
			for (Long selectedAuthor : selectedAuthors) {
				authorDtos.add(authorService.getById(selectedAuthor));
			}
			bookDto.setAuthors(authorDtos);
			this.selectedBook.setMatter(matterService.getById(selectedMatter));
			bookService.update(this.selectedBook);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_book_edited");
        }
		books = bookService.getAll();
        PrimeFaces.current().executeScript("PF('manageBookDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-book");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
	}

	//Permite eliminar un usuario
    public void deleteBook() {
    	try {
    		bookService.delete(this.selectedBook.getBookId());
            this.selectedBook = null;
			books = bookService.getAll();
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_book_deleted");
            PrimeFaces.current().ajax().update("form:dt-book");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
    }

	public List<AuthorDto> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorDto> authors) {
		this.authors = authors;
	}

	public Long[] getSelectedAuthors() {
		return selectedAuthors;
	}

	public void setSelectedAuthors(Long[] selectedAuthors) {
		this.selectedAuthors = selectedAuthors;
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

	public void setSelectedBook(BookDto selectedBook) {
		this.selectedBook = selectedBook;
	}

	public List<BookDto> getBooks() {
		return this.books;
	}

	public void setBooks(List<BookDto> books) {
		this.books = books;
	}

	public Long getSelectedMatter() {
		return selectedMatter;
	}

	public void setSelectedMatter(Long selectedMatter) {
		this.selectedMatter = selectedMatter;
	}

	public List<MatterDto> getMatters() {
		return matters;
	}

	public void setMatters(List<MatterDto> matters) {
		this.matters = matters;
	}
}
