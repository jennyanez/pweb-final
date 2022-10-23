package cu.edu.cujae.pweb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.LoanDto;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podrï¿½ inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class LoanServiceImpl implements LoanService{

	@Override
	public List<LoanDto> getLoans() {
		
		List<LoanDto> loans = new ArrayList<>();
		loans.add(new LoanDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "El gatito", 12, "Jennifer Yanez", new Date(), new Date()));
		loans.add(new LoanDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Tuyo", 17, "Cloe Yanez", new Date(), new Date()));
        loans.add(new LoanDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Te perdio", 6, "Bad Bunny", new Date(), new Date()));

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

}