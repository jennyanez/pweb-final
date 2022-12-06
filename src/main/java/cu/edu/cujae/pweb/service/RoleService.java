package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.pweb.dto.XRoleDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import cu.edu.cujae.pweb.utils.ServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

@Service
public class RoleService implements ServiceImplementation {
	@Autowired
	private RestService restService;

	public List<XRoleDto> getRolesByUser(Long userId) {
		return null;
	}


	public List<XRoleDto> getRolesByName(String name) {
		return null;
	}


	@Override
	public  List<XRoleDto> getAll() {
		List<XRoleDto> roles = new ArrayList<XRoleDto>();
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<XRoleDto> apiRestMapper = new ApiRestMapper<>();
			String response = (String)restService.GET("/roles/all", params, String.class).getBody();
			roles = apiRestMapper.mapList(response, XRoleDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return roles;
	}

	@Override
	public XRoleDto getById(Long id) {
		XRoleDto role = null;
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<XRoleDto> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/roles/{id}");
			String uri = template.expand(id).toString();
			String response = (String)restService.GET(uri, params, String.class).getBody();
			role = apiRestMapper.mapOne(response, XRoleDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	@Override
	public void create(Object dto) {
		XRoleDto user = (XRoleDto) dto;
		String response = (String) restService.POST("/roles/save", user, String.class).getBody();
		System.out.println(response);

	}

	@Override
	public void update(Object dto) {
		XRoleDto user = (XRoleDto) dto;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		String response = (String) restService.PUT("/roles/update/{id}", params, user, String.class).getBody();
		System.out.println(response);

	}


	@Override
	public void delete(Long id) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/roles/delete/{id}");
		String uri = template.expand(id).toString();
		String response = (String) restService.DELETE(uri, params, String.class).getBody();
		System.out.println(response);
	}
}
