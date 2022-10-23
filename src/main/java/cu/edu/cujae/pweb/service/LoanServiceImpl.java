package cu.edu.cujae.pweb.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.LoanDto;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podrï¿½ inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class LoanServiceImpl implements LoanService{
	
	@Autowired 
	private LoanService loanService;

	@Override
	public List<LoanDto> getLoans() {
		
		List<LoanDto> loans = new ArrayList<>();
		loans.add(new LoanDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "El gatito", 12, "Jennifer Yanez", new Date(2022, 5, 8), new Date(2022, 7, 8)));
		loans.add(new LoanDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Tuyo", 12, "Cloe Yanez", new Date(2022, 1, 8), new Date(2022, 11, 8)));
        loans.add(new LoanDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Te perdio", 12, "Bad Bunny", new Date(2022, 3, 8), new Date(2022, 9, 8)));

		return loans;
	}

	@Override
	public LoanDto getLoanById(String loanId) {
		return getLoans().stream().filter(r -> r.getLoanId().equals(loanId)).findFirst().get();
	}

	@Override
	public void createLoan(LoanDto loan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateLoan(LoanDto loan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLoan(String id) {
		// TODO Auto-generated method stub
		
	}

	public LoanService getLoanService() {
		return loanService;
	}

	public void setLoanService(LoanService loanService) {
		this.loanService = loanService;
	}
	
}