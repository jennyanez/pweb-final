package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.pweb.dto.AuthorDto;
import cu.edu.cujae.pweb.dto.XUserDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import cu.edu.cujae.pweb.utils.ServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

@Service
public class AuthorService implements ServiceImplementation {
	@Autowired
	private RestService restService;

	@Override
	public List<AuthorDto> getAll() {
		List<AuthorDto> authorDtoList = new ArrayList<>();
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<AuthorDto> apiRestMapper = new ApiRestMapper<>();
			String response = (String)restService.GET("/authors/all", params, String.class).getBody();
			authorDtoList = apiRestMapper.mapList(response, AuthorDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return authorDtoList;
	}

	@Override
	public AuthorDto getById(Long id) {
		AuthorDto authorDto = null;
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<AuthorDto> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/authors/{id}");
			String uri = template.expand(id).toString();
			String response = (String)restService.GET(uri, params, String.class).getBody();
			authorDto = apiRestMapper.mapOne(response, AuthorDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return authorDto;
	}

	@Override
	public void create(Object author) {
		AuthorDto authorDto = (AuthorDto) author;
		String response = (String) restService.POST("/authors/save", authorDto, String.class).getBody();
		System.out.println(response);
	}

	@Override
	public void update(Object author) {
		AuthorDto authorDto = (AuthorDto) author;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		String response = (String) restService.PUT("/authors/update", params, authorDto, String.class).getBody();
		System.out.println(response);
	}

	@Override
	public void delete(Long id) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/authors/delete/{id}");
		String uri = template.expand(id).toString();
		String response = (String) restService.DELETE(uri, params, String.class).getBody();
		System.out.println(response);
	}
}
