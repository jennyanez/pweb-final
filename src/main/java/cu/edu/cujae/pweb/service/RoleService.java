package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.pweb.dto.RoleDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
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


	@Override
	public  List<RoleDto> getAll() {
		List<RoleDto> roles = new ArrayList<RoleDto>();
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<RoleDto> apiRestMapper = new ApiRestMapper<>();
			String response = (String)restService.GET("/api/v1/roles/all", params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
			roles = apiRestMapper.mapList(response, RoleDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return roles;
	}

	@Override
	public RoleDto getById(Long id) {
		RoleDto role = null;
		int code = Math.toIntExact(id);
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<RoleDto> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/api/v1/roles/{code}");
			String uri = template.expand(code).toString();
			String response = (String)restService.GET(uri, params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
			role = apiRestMapper.mapOne(response, RoleDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	@Override
	public String create(Object dto) {
		RoleDto user = (RoleDto) dto;
		String response = (String) restService.POST("/api/v1/roles/save", user, String.class,CurrentUserUtils.getTokenBearer()).getBody();
		System.out.println(response);

		return response;
	}

	@Override
	public String update(Object dto) {
		RoleDto user = (RoleDto) dto;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		String response = (String) restService.PUT("/api/v1/roles/", params, user, String.class,CurrentUserUtils.getTokenBearer()).getBody();
		System.out.println(response);

		return response;
	}

	@Override
	public String delete(Long id) {
		int code = Math.toIntExact(id);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/roles/delete/{code}");
		String uri = template.expand(code).toString();
		String response = (String) restService.DELETE(uri, params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
		System.out.println(response);
		return response;
	}
}
