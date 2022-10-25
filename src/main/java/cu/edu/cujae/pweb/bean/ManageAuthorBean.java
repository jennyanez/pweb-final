package cu.edu.cujae.pweb.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.AuthorDto;
import cu.edu.cujae.pweb.dto.BookDto;
import cu.edu.cujae.pweb.service.BookService;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageAuthorBean {
	
	private AuthorDto author;
	private AuthorDto selectedAuthor;
	private List<AuthorDto> authors;
	//private Long[] selectedRoles;
	
	private List<BookDto> books;
	
	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
/*	@Autowired
	private AuthorService authorService;
*/	
	@Autowired
	private BookService bookService;
	
	
	public ManageAuthorBean() {
		
	}
	
	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase. 
	@PostConstruct
    public void init() {
	//    authors = authors == null ? authorService.getAuthors() : authors;
		books = bookService.getBooks();
    }
	
	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
        this.selectedAuthor = new AuthorDto();
    //    this.selectedRoles = null;
    }
	
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
	//	List<XRoleDto> roles = this.selectedUser.getRoles();
	//	this.selectedRoles = roles.stream().map(r -> r.getId()).toArray(Long[]::new);
	}
	
	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveAuthor() {
  /*      if (this.selectedUser.getId() == null) {
            this.selectedUser.setId(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            this.selectedUser.setNewRecord(true);
            List<XRoleDto> rolesToAdd = new ArrayList<XRoleDto>();
            for(int i = 0; i < this.selectedRoles.length; i++) {
            	rolesToAdd.add(roleService.getRolesById(selectedRoles[i]));
            }
            
            this.users.add(this.selectedUser);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_edited");
        }

        PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-users");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
   */
    }

	//Permite eliminar un usuario
    public void deleteAuthor() {
  /*  	try {
    		this.users.remove(this.selectedUser);
            this.selectedUser = null;
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_removed");
            PrimeFaces.current().ajax().update("form:dt-users");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
     */   
    }
    
    
/********************** GETTERS AND SETTERS ***********************/
	public AuthorDto getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDto author) {
		this.author = author;
	}

	public AuthorDto getSelectedAuthor() {
		return selectedAuthor;
	}

	public void setSelectedAuthor(AuthorDto selectedAuthor) {
		this.selectedAuthor = selectedAuthor;
	}

	public List<AuthorDto> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorDto> authors) {
		this.authors = authors;
	}

	public List<BookDto> getBooks() {
		return books;
	}

	public void setBooks(List<BookDto> books) {
		this.books = books;
	}



}
