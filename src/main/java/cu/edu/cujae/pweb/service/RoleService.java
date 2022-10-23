package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.XRoleDto;

public interface RoleService {
	List<XRoleDto> getRoles();
	List<XRoleDto> getRolesByUser(Long userId);
	List<XRoleDto> getRolesByName(String name);
	XRoleDto getRolesById(Long roleId);
}
