package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.pweb.dto.UserDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import cu.edu.cujae.pweb.utils.ServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

@Service
public class UserService implements ServiceImplementation {

	@Autowired
	private RoleService roleService;
	@Autowired
	private RestService restService;

	@Override
	public  List<UserDto> getAll() {
		List<UserDto> users = new ArrayList<UserDto>();
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();
			String response = (String)restService.GET("/users/all", params, String.class).getBody();
			users = apiRestMapper.mapList(response, UserDto.class);
			for(UserDto u : users){
				u.rolesName();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public UserDto getById(Long id) {
		UserDto user = null;
		int code = Math.toIntExact(id);

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/users/{code}");
			String uri = template.expand(code).toString();
			String response = (String)restService.GET(uri, params, String.class).getBody();
			user = apiRestMapper.mapOne(response, UserDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void create(Object dto) {
		UserDto user = (UserDto) dto;
		String response = (String) restService.POST("/users/save", user, String.class).getBody();
		System.out.println(response);
	}

	@Override
	public void update(Object dto) {
		UserDto user = (UserDto) dto;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		String response = (String) restService.PUT("/users/update", params, user, String.class).getBody();
		System.out.println(response);
	}

	@Override
	public void delete(Long id) {
		int code = Math.toIntExact(id);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/users/delete/{code}");
		String uri = template.expand(code).toString();
		String response = (String) restService.DELETE(uri, params, String.class).getBody();
		System.out.println(response);
	}
}