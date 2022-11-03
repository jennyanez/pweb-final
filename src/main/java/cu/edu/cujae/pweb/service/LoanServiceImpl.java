package cu.edu.cujae.pweb.service;

import java.util.ArrayList;
import java.util.List;

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
	/*	loans.add(new LoanDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Origen", 12, "Jennifer Yanez", new Date(), new Date(), true));
		loans.add(new LoanDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Orgullo y prejuicio", 17, "Andy Piloto", new Date(), new Date(), true));
        loans.add(new LoanDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "El hombre que amaba a los perros", 6, "Ana Gonzalez", new Date(), new Date(), true));
	*/
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