package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.DefaulterUserDto;

public interface DefaulterUserService {
	List<DefaulterUserDto> getDefUsers();
	DefaulterUserDto getDefUserById(String defUserId);
	void deleteDefUser(String id);
}
