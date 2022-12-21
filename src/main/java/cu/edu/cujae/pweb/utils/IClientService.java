package cu.edu.cujae.pweb.utils;

import java.util.List;

import cu.edu.cujae.pweb.dto.LoanDto;

public interface IClientService {
	public List<LoanDto> LoanByClientId(Long idClient);
}
