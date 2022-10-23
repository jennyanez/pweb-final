package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.XUserDto;

public interface UserService {
	List<XUserDto> getUsers();
	XUserDto getUserById(String userId);
	void createUser(XUserDto user);
	void updateUser(XUserDto user);
	void deleteUser(String id);
}
