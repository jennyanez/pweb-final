package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.DefaulterClientDto;


public interface DefaulterClientService {
	List<DefaulterClientDto> getDefUsers();
	DefaulterClientDto getDefUserById(String defUserId);
	void deleteDefUser(String id);
}
