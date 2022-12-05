package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.LoanRequestDto;

public interface LoanRequestService {
	List<LoanRequestDto> getLoansRequest();
	LoanRequestDto getLoanRequestById(Long loanId);
	void createLoanRequest(LoanRequestDto loan);
	void updateLoanRequest(LoanRequestDto loan);
	void deleteLoanRequest(Long id);
}
