package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.pweb.dto.UserDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import cu.edu.cujae.pweb.utils.ServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
			String response = (String)restService.GET("/api/v1/users/all", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
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

			UriTemplate template = new UriTemplate("/api/v1/users/{code}");
			String uri = template.expand(code).toString();
			String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
			user = apiRestMapper.mapOne(response, UserDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public String create(Object dto) {
		String msg = "";
		UserDto user = (UserDto) dto;
		ResponseEntity response = restService.POST("/api/v1/users/save", user, String.class, CurrentUserUtils.getTokenBearer());
		if(response.getStatusCode().isError()){
			return msg = (String)response.getBody();
		}
		return msg;
	}

	@Override
	public String update(Object dto) {
		String msg = "";
		UserDto user = (UserDto) dto;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		ResponseEntity response = restService.PUT("/api/v1/users/update", params, user, String.class, CurrentUserUtils.getTokenBearer());
		if(response.getStatusCode().isError()){
			return msg = (String)response.getBody();
		}
		return msg;
	}

	@Override
	public String delete(Long id) {
		String msg = "";
		int code = Math.toIntExact(id);
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/users/delete/{code}");
		String uri = template.expand(code).toString();
		ResponseEntity response = restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer());
		if(response.getStatusCode().isError()){
			return msg = (String)response.getBody();
		}
		return msg;
	}

	public UserDto getByEmail(String email) {
		//String msg = "";
		UserDto user = null;
		try{

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();

		UriTemplate template = new UriTemplate("/api/v1/users/email/{email}");
		String uri = template.expand(email).toString();
		String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		user = apiRestMapper.mapOne(response, UserDto.class);
	} catch (Exception e) {
		e.printStackTrace();
	}
		return user;
	}

	public UserDto getByUsername(String username) {
		//String msg = "";
		UserDto user = null;
		try{
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();

		UriTemplate template = new UriTemplate("/api/v1/users/username/{username}");
		String uri = template.expand(username).toString();
		String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
			user = apiRestMapper.mapOne(response, UserDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}