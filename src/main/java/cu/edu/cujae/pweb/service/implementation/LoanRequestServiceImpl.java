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
	public List<LoanRequestDto> getLoansRequest(){
		
		List<LoanRequestDto> loansRequest = new ArrayList<>();
//		loansRequest.add(new LoanRequestDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Andy", "Harry Potter", 10, new Date(), true));
//		loansRequest.add(new LoanRequestDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Jenny", "El Amor en Tiempos de Colera", 20, new Date(), true));
//        loansRequest.add(new LoanRequestDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Ana", "Viaje al centro de la Tierra", 30, new Date(), true));

		return loansRequest;
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
