package service;

import java.util.List;

import dto.WebRoleDto;

public interface RoleService {
	List<WebRoleDto> getRoles();
	List<WebRoleDto> getRolesByUser(Long userId);
	List<WebRoleDto> getRolesByName(String name);
	WebRoleDto getRolesById(Long roleId);
}
