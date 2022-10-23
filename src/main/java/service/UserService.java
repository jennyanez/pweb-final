package service;

import java.util.List;

import dto.WebUserDto;

public interface UserService {
	List<WebUserDto> getUsers();
	WebUserDto getUserById(String userId);
	void createUser(WebUserDto user);
	void updateUser(WebUserDto user);
	void deleteUser(String id);
}
