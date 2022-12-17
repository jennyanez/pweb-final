package cu.edu.cujae.pweb.bean.managebeans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import cu.edu.cujae.pweb.dto.AuthorDto;
import cu.edu.cujae.pweb.dto.CopyDto;
import cu.edu.cujae.pweb.dto.MatterDto;
import cu.edu.cujae.pweb.service.AuthorService;
import cu.edu.cujae.pweb.service.CopyService;
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
	private int amountCopies;

	/**** SERVICES*****/
	@Autowired
	private BookService bookService;
	@Autowired
	private MatterService matterService;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private CopyService copyService;
	
	public ManageBookBean() {
		
	}
	
	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase. 
	@PostConstruct
    public void init() {
		books = bookService.getAll();
		matters = matterService.getAll();
		authors = authorService.getAll();
		amountCopies = 0;
    }
	
	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
		this.selectedBook = new BookDto();
		this.selectedMatter = null;
		this.selectedAuthors = null;
		amountCopies = 0;
    }
	
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
		MatterDto matter = this.selectedBook.getMatter();
		this.selectedMatter = matter.getMatterId();

		List<AuthorDto> authorDtos = this.selectedBook.getAuthors();
		this.selectedAuthors = authorDtos.stream().map(AuthorDto::getAuthorId).toArray(Long[]::new);

		amountCopies = 0;
	}

	
	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveBook() {
		if (this.selectedBook.getBookId() == null) {
            bookDto = selectedBook;

			///establece materia
			bookDto.setMatter(matterService.getById(selectedMatter));

			//establece autores
			List<AuthorDto> authorDtos = new ArrayList<>();
			for (Long selectedAuthor : selectedAuthors) {
				authorDtos.add(authorService.getById(selectedAuthor));
			}
			bookDto.setAuthors(authorDtos);

			//crea el libro
			bookService.create(bookDto);

			//actualizo lista de libros
			books = bookService.getAll();

			//crea las copias y las guarda
			insertCopies(amountCopies);

            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_book_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
			/////codigo para updatear
			//updatea autores
			List<AuthorDto> authorDtos = new ArrayList<>();
			for (Long selectedAuthor : selectedAuthors) {
				authorDtos.add(authorService.getById(selectedAuthor));
			}
			if(authorDtos.size()!=0){
				bookDto.setAuthors(authorDtos);
			}

			//updatea materia
			this.selectedBook.setMatter(matterService.getById(selectedMatter));

			// updatea el libro
			bookService.update(this.selectedBook);
			books = bookService.getAll();
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_book_edited");
        }
        PrimeFaces.current().executeScript("PF('manageBookDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-book");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form

//		ManageCopyBean manageCopyBean = new ManageCopyBean();
//		manageCopyBean.updateAjax();

	}

	//Permite eliminar un usuario
    public void deleteBook() {
    	try {
			///elimina las copias de ese libro
			/// pues sin libro no hay copia, duhh
			List<CopyDto> copiesList = copyService.getByBookId(this.selectedBook.getBookId());
			for(CopyDto c:copiesList){
				copyService.delete(c.getCopyId());
			}

			////ahora si elimino el libro
    		bookService.delete(this.selectedBook.getBookId());
            this.selectedBook = null;

			//actualizo lista de libros
			books = bookService.getAll();

            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_book_deleted");
            PrimeFaces.current().ajax().update("form:dt-book");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form

		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
    }

	public void insertCopies(int amountCopies){
		if(amountCopies > 0){
			for(int i=0; i<amountCopies; i++){
				CopyDto copy = new CopyDto();
				copy.setCopyNumber(i+1);
				copy.setBook(books.get(books.size()-1));
				copyService.create(copy);
			}
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

	public int getAmountCopies() {
		return amountCopies;
	}

	public void setAmountCopies(int amountCopies) {
		this.amountCopies = amountCopies;
	}


}
