package cu.edu.cujae.pweb.bean.managebeans;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import cu.edu.cujae.pweb.service.BookService;
import cu.edu.cujae.pweb.service.ClientService;
import cu.edu.cujae.pweb.service.CopyService;
import cu.edu.cujae.pweb.service.LoanRequestService;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.BookDto;
import cu.edu.cujae.pweb.dto.ClientDto;
import cu.edu.cujae.pweb.dto.CopyDto;
import cu.edu.cujae.pweb.dto.LoanRequestDto;
import cu.edu.cujae.pweb.utils.JsfUtils;

@Component 
@ManagedBean
@ViewScoped
public class ManageLoanRequestBean {
	
	private LoanRequestDto loanRequestDto;
	private LoanRequestDto selectedLoanRequest;
	private Long selectedCopy;
	private Long selectedBook;
	private Long selectedClient;
	
	private List<LoanRequestDto> loansRequest;
	private List<ClientDto> clients;
	private List<CopyDto> copies;
	private List<BookDto> books;
	
    @Autowired(required=true)
	private LoanRequestService loanRequestService;
    
    @Autowired
    private CopyService copyService;
    
    @Autowired
    private ClientService clientService;
    
    @Autowired
    private BookService bookService;
	
	public ManageLoanRequestBean() {
		
	}
	
	@PostConstruct
    public void init() {
	    loansRequest = loanRequestService.getAll();
	    clients = clientService.getAll();
	    books = bookService.getAll();
	    copies = copyService.getByBookId(selectedBook);
	    
    }
	
	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
        this.selectedLoanRequest = new LoanRequestDto();
        this.selectedClient = null;
        this.selectedCopy = null;
        this.selectedBook = null;
	}
	
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
			ClientDto client = this.selectedLoanRequest.getClient();
			CopyDto copy = this.selectedLoanRequest.getCopy();
			BookDto book = this.selectedLoanRequest.getBook();
			
			this.selectedClient = client.getClientId();
			this.selectedCopy = copy.getCopyId();
			this.selectedBook = book.getBookId();
	}
	
	public void saveLoan() {
			
		if (this.selectedLoanRequest.getId() == null) {
           
           this.selectedLoanRequest.setClient(this.clientService.getById(selectedClient));
           this.selectedLoanRequest.setCopy(this.copyService.getById(selectedCopy));
           this.selectedLoanRequest.setBook(this.bookService.getById(selectedBook));
           loanRequestService.create(this.selectedLoanRequest);
           loansRequest = loanRequestService.getAll();
            
           JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_loanRequest_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
        	
        	this.selectedLoanRequest.setClient(this.clientService.getById(selectedClient));
            this.selectedLoanRequest.setCopy(this.copyService.getById(selectedCopy));
            this.selectedLoanRequest.setBook(this.bookService.getById(selectedBook));
            loanRequestService.update(this.selectedLoanRequest);
            loansRequest = loanRequestService.getAll();
        	
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_loanRequest_edited");
        }
        PrimeFaces.current().executeScript("PF('manageLoanRequestDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-loanRequest");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form	
	}
	
	//Permite eliminar un usuario
    public void deleteLoan() {
    	
    	try {
    		loanRequestService.delete(this.selectedLoanRequest.getId());
            this.selectedLoanRequest = null;
            loansRequest = loanRequestService.getAll();
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_loanRequest_removed");
            PrimeFaces.current().ajax().update("form:dt-loanRequest");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
    	
    }

    public void updateAjax(){
		PrimeFaces.current().ajax().update("form:dt-loanRequest");
	}

    
    ////////////////////////////////////////////////      SETTERS Y GETTERS    ////////////////////////////////////////////////////*

    public LoanRequestDto getLoanRequestDto() {
		return loanRequestDto;
	}

	public void setLoanRequestDto(LoanRequestDto loanRequestDto) {
		this.loanRequestDto = loanRequestDto;
	}

	public LoanRequestDto getSelectedLoanRequest() {
		return selectedLoanRequest;
	}

	public void setSelectedLoanRequest(LoanRequestDto selectedLoanRequest) {
		this.selectedLoanRequest = selectedLoanRequest;
	}

	public Long getSelectedCopy() {
		return selectedCopy;
	}

	public void setSelectedCopy(Long selectedCopy) {
		this.selectedCopy = selectedCopy;
	}

	public Long getSelectedBook() {
		return selectedBook;
	}

	public void setSelectedBook(Long selectedBook) {
		this.selectedBook = selectedBook;
	}

	public Long getSelectedClient() {
		return selectedClient;
	}

	public void setSelectedClient(Long selectedClient) {
		this.selectedClient = selectedClient;
	}

	public List<LoanRequestDto> getLoansRequest() {
		return loansRequest;
	}

	public void setLoansRequest(List<LoanRequestDto> loansRequest) {
		this.loansRequest = loansRequest;
	}

	public List<ClientDto> getClients() {
		return clients;
	}

	public void setClients(List<ClientDto> clients) {
		this.clients = clients;
	}

	public List<CopyDto> getCopies() {
		return copies;
	}

	public void setCopies(List<CopyDto> copies) {
		this.copies = copies;
	}

	public List<BookDto> getBooks() {
		return books;
	}

	public void setBooks(List<BookDto> books) {
		this.books = books;
	}
    
}
