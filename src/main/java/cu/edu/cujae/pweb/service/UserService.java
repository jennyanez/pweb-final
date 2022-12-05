package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.pweb.dto.XUserDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.CrudInterface;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

@Service
public class UserService implements CrudInterface {

	@Autowired
	private RoleService roleService;
	@Autowired
	private RestService restService;

	@Override
	public  List<XUserDto> getAll() {
		List<XUserDto> users = new ArrayList<XUserDto>();
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<XUserDto> apiRestMapper = new ApiRestMapper<>();
			String response = (String)restService.GET("/users/all", params, String.class).getBody();
			users = apiRestMapper.mapList(response, XUserDto.class);
//			for(XUserDto u : users){
//				u.rolesName();
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public XUserDto getById(Long id) {
		XUserDto user = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<XUserDto> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/users/{id}");
			String uri = template.expand(id).toString();
			String response = (String)restService.GET(uri, params, String.class).getBody();
			user = apiRestMapper.mapOne(response, XUserDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void create(Object dto) {
		XUserDto user = (XUserDto) dto;
		String response = (String) restService.POST("/users/save", user, String.class).getBody();
		System.out.println(response);
	}

	@Override
	public void update(Object dto) {
		XUserDto user = (XUserDto) dto;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		String response = (String) restService.PUT("/users/update/{id}", params, user, String.class).getBody();
		System.out.println(response);
	}

	@Override
	public void delete(Long code) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/users/delete/{id}");
		String uri = template.expand(code).toString();
		String response = (String) restService.DELETE(uri, params, String.class).getBody();
		System.out.println(response);
	}
}