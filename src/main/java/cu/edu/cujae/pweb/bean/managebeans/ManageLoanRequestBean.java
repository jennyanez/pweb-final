package cu.edu.cujae.pweb.bean.managebeans;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import cu.edu.cujae.pweb.dto.LoanRequestDto;
import cu.edu.cujae.pweb.service.LoanRequestService;
import cu.edu.cujae.pweb.utils.JsfUtils;

@Component 
@ManagedBean
@ViewScoped
public class ManageLoanRequestBean {
	private LoanRequestDto loanRequestDto;
	private LoanRequestDto selectedLoanRequest;
	private List<LoanRequestDto> loansRequest;
	
	@Autowired
	private LoanRequestService loanRequestService;
	
	public ManageLoanRequestBean() {
		
	}
	
	@PostConstruct
    public void init() {
	    loansRequest = loansRequest == null ? loanRequestService.getLoansRequest() : loansRequest;
    }
	
	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
        this.setSelectedLoanRequest(new LoanRequestDto());
	}
	
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
			
	}
	
	public void saveLoan() {
			
		if (this.selectedLoanRequest.getId() == null) {
            this.selectedLoanRequest.setId(Long.valueOf(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9)));
            this.selectedLoanRequest.setLoanRequestDate(new Date());
            this.loansRequest.add(this.selectedLoanRequest);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_loanRequest_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_loanRequest_edited");
        }
        PrimeFaces.current().executeScript("PF('manageLoanRequestDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-loanRequest");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form	
	}
	
	//Permite eliminar un usuario
    public void deleteLoan() {
    	
    	try {
    		this.loansRequest.remove(this.selectedLoanRequest);
            this.selectedLoanRequest = null;
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_loanRequest_removed");
            PrimeFaces.current().ajax().update("form:dt-loanRequest");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
    	
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

	public List<LoanRequestDto> getLoansRequest() {
		return loansRequest;
	}

	public void setLoansRequest(List<LoanRequestDto> loansRequest) {
		this.loansRequest = loansRequest;
	}
			
}
