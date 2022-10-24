package cu.edu.cujae.pweb.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.springframework.stereotype.Component;


import cu.edu.cujae.pweb.dto.LoanRequestDto;

@Component 
@ManagedBean
@ViewScoped
public class ManageLoanRequestBean {
	private LoanRequestDto loanRequestDto;
	private LoanRequestDto selectedLoanRequest;
	private List<LoanRequestDto> loansRequest;
	
	//@Autowired
	//private LoanRequestService loanService;
	
	public ManageLoanRequestBean() {
		
	}
	
	@PostConstruct
    public void init() {
	    //loansRequest = loansRequest == null ? loanService.getLoans() : loansRequest;
    }
	
	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
        this.setSelectedLoanRequest(new LoanRequestDto());
	}
	
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
			
	}
	
	public void saveLoan() {
			//Falta la implementacion 
	}
	
	//Permite eliminar un usuario
    public void deleteLoan() {
    	//Falta la implementacion
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
