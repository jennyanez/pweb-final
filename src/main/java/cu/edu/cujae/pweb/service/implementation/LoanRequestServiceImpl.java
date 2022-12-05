package cu.edu.cujae.pweb.service.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import cu.edu.cujae.pweb.service.LoanRequestService;
import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.LoanRequestDto;

@Service
public class LoanRequestServiceImpl implements LoanRequestService {

	@Override
	public List<LoanRequestDto> getLoansRequest() {
		return null;
	}

	@Override
	public LoanRequestDto getLoanRequestById(Long loanId) {
		return null;
	}

	@Override
	public void createLoanRequest(LoanRequestDto loan) {

	}

	@Override
	public void updateLoanRequest(LoanRequestDto loan) {

	}

	@Override
	public void deleteLoanRequest(Long id) {

	}
}
