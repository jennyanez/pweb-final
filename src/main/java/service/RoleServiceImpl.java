package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dto.WebRoleDto;

@Service
public class RoleServiceImpl implements RoleService{

	@Override
	public List<WebRoleDto> getRoles() {
		List<WebRoleDto> roles = new ArrayList<>();
		roles.add(new WebRoleDto(1L, "admin", "Administrador del sistema"));
		roles.add(new WebRoleDto(2L, "employee", "Empleado de la empresa"));
		roles.add(new WebRoleDto(3L, "asesor", "Asesor de la empresa"));
		roles.add(new WebRoleDto(4L, "manager", "Manager de la empresa"));
		roles.add(new WebRoleDto(5L, "reporter", "Visualizador de reportes"));
		return roles;
	}

	@Override
	public List<WebRoleDto> getRolesByUser(Long userId) {
		return getRoles().stream().filter(r -> r.getId() == userId).collect(Collectors.toList());
	}

	@Override
	public List<WebRoleDto> getRolesByName(String name) {
		return getRoles().stream().filter(r -> r.getRoleName().equals(name)).collect(Collectors.toList());
	}

	@Override
	public WebRoleDto getRolesById(Long roleId) {
		return getRoles().stream().filter(r -> r.getId().equals(roleId)).findFirst().get();
	}

}
