package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.LoanDto;

public interface LoanService {
	List<LoanDto> getLoans();
	LoanDto getLoanById(String loanId);
	void createLoan(LoanDto loan);
	void updateLoan(LoanDto loan);
	void deleteLoan(String id);
}
