package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.SanctionedUserDto;

public interface SanctionedUserService {
	List<SanctionedUserDto> getSanctUsers();
	SanctionedUserDto getSanctUserById(String sanctUserId);
}
