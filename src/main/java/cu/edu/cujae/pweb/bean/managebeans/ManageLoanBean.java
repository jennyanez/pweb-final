package cu.edu.cujae.pweb.bean.managebeans;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.LoanDto;
import cu.edu.cujae.pweb.dto.LoanRequestDto;
import cu.edu.cujae.pweb.service.LoanRequestService;
import cu.edu.cujae.pweb.service.LoanService;
import cu.edu.cujae.pweb.utils.JsfUtils;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageLoanBean {
	
	private LoanDto loanDto;
	private LoanDto selectedLoan;
	private Long selectedLoanRequest;
	private List<LoanDto> loans;
	private List<LoanRequestDto> loansRequest;
	
	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private LoanRequestService loanRequestService;
	
	
	public ManageLoanBean() {
		
	}
	
	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase. 
	
	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
        this.selectedLoan = new LoanDto();
        this.selectedLoanRequest = null;
        System.out.println("hola2");
    }
	
	//Se ejecuta al dar clic en el button con el lapicito
	
	public void openForEdit() {
	
	}
	
	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveLoan() {
		String msg = "";
		LoanRequestDto loanRequest = loanRequestService.getById(selectedLoanRequest);
		
		if (this.selectedLoan.getId() == null) {

			if (loanService.amountLoan(loanRequest.getClient().getClientId()) < 3) {

				this.selectedLoan.setClient(loanRequest.getClient());
				this.selectedLoan.setCopy(loanRequest.getCopy());
				this.selectedLoan.setLoanDate(new Date());
				msg = loanService.create(selectedLoan);
				loans = loanService.getAll();
			//	System.out.println("anntes de entrar al delete");
				msg = loanRequestService.delete(selectedLoanRequest);

				JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_loan_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
			}
			else{
				JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_client_cannot_have_more_books");
			}
		}
        else {
        	
        	this.selectedLoan.setClient(loanRequest.getClient());
			this.selectedLoan.setCopy(loanRequest.getCopy());
			this.selectedLoan.setLoanDate(new Date());
			msg = loanService.update(selectedLoan);
			loans = loanService.getAll();
			
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_loan_edited");
        }

        PrimeFaces.current().executeScript("PF('manageLoanDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-loan");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
     
	}

	//Permite eliminar un usuario
    public void deleteLoan() {
		String msg = "";
    	try {
    		msg = loanService.delete(this.selectedLoan.getId());
            this.selectedLoan = null;
            loans = loanService.getAll();
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_loan_deleted");
            PrimeFaces.current().ajax().update("form:dt-loan");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
    }

	
    
/*********************************GETTERS AND SETTERS***************************/
    public LoanDto getLoanDto() {
		return loanDto;
	}

	public void setLoanDto(LoanDto loanDto) {
		this.loanDto = loanDto;
	}

	public LoanDto getSelectedLoan() {
		return selectedLoan;
	}

	public void setSelectedLoan(LoanDto selectedLoan) {
		this.selectedLoan = selectedLoan;
	}

	public Long getSelectedLoanRequest() {
		return selectedLoanRequest;
	}

	public void setSelectedLoanRequest(Long selectedLoanRequest) {
		this.selectedLoanRequest = selectedLoanRequest;
	}

	public List<LoanDto> getLoans() {
		loans = loanService.getAll();
		return loans;
	}

	public void setLoans(List<LoanDto> loans) {
		this.loans = loans;
	}

	public List<LoanRequestDto> getLoansRequest() {
		loansRequest = loanRequestService.getAll();
		return loansRequest;
	}

	public void setLoansRequest(List<LoanRequestDto> loansRequest) {
		this.loansRequest = loansRequest;
	}

}
