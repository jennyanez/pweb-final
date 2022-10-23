package cu.edu.cujae.pweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.XRoleDto;

@Service
public class RoleServiceImpl implements RoleService{

	@Override
	public List<XRoleDto> getRoles() {
		List<XRoleDto> roles = new ArrayList<>();
		roles.add(new XRoleDto(1L, "admin", "Administrador del sistema"));
		roles.add(new XRoleDto(2L, "employee", "Empleado de la empresa"));
		roles.add(new XRoleDto(3L, "asesor", "Asesor de la empresa"));
		roles.add(new XRoleDto(4L, "manager", "Manager de la empresa"));
		roles.add(new XRoleDto(5L, "reporter", "Visualizador de reportes"));
		return roles;
	}

	@Override
	public List<XRoleDto> getRolesByUser(Long userId) {
		return getRoles().stream().filter(r -> r.getId() == userId).collect(Collectors.toList());
	}

	@Override
	public List<XRoleDto> getRolesByName(String name) {
		return getRoles().stream().filter(r -> r.getRoleName().equals(name)).collect(Collectors.toList());
	}

	@Override
	public XRoleDto getRolesById(Long roleId) {
		return getRoles().stream().filter(r -> r.getId().equals(roleId)).findFirst().get();
	}

}
