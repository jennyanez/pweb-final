package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.SanctionedClientDto;


public interface SanctionedClientService {
	List<SanctionedClientDto> getSanctUsers();
	SanctionedClientDto getSanctUserById(String sanctUserId);
}
