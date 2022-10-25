package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.LoanRequestDto;

public interface LoanRequestService {
	List<LoanRequestDto> getLoansRequest();
	LoanRequestDto getLoanRequestById(String loanId);
	void createLoanRequest(LoanRequestDto loan);
	void updateLoanRequest(LoanRequestDto loan);
	void deleteLoanRequest(String id);
}
