package cu.edu.cujae.pweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.LoanRequestDto;

@Service
public class LoanRequestServiceImpl implements LoanRequestService{
	
	@Override
	public List<LoanRequestDto> getLoansRequest() {
			return null;
		}
	
	@Override
	public LoanRequestDto getLoanRequestById(Long loanId) {
		return getLoansRequest().stream().filter(r -> r.getId().equals(loanId)).findFirst().get();
	}
	@Override
	public void createLoanRequest(LoanRequestDto loan) {
		
	}
	
	@Override
	public void updateLoanRequest(LoanRequestDto loan) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void deleteLoanRequest(Long id) {
		
	}
}
