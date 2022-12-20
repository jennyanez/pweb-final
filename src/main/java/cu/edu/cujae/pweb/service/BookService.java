package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.pweb.dto.AuthorDto;
import cu.edu.cujae.pweb.dto.BookDto;
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
public class BookService implements ServiceImplementation {
	@Autowired
	private RestService restService;

	@Override
	public List<BookDto> getAll() {
		List<BookDto> bookDtoList = new ArrayList<>();
		try{
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<BookDto> apiRestMapper = new ApiRestMapper<>();
			String response = (String)restService.GET("/api/v1/books/all", params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
			bookDtoList = apiRestMapper.mapList(response, BookDto.class);
			for(BookDto b: bookDtoList){
				b.authorsName();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bookDtoList;
	}

	@Override
	public BookDto getById(Long id) {
		BookDto bookDto = null;
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<BookDto> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/api/v1/books/{id}");
			String uri = template.expand(id).toString();
			String response = (String)restService.GET(uri, params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
			bookDto = apiRestMapper.mapOne(response, BookDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bookDto;
	}

	@Override
	public void create(Object book) {
		BookDto bookDto = (BookDto) book;
		String response = (String) restService.POST("/api/v1/books/save", bookDto, String.class,CurrentUserUtils.getTokenBearer()).getBody();
		System.out.println(response);
	}

	@Override
	public void update(Object book) {
		BookDto bookDto = (BookDto) book;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		String response = (String) restService.PUT("/api/v1/books/update", params, bookDto, String.class,CurrentUserUtils.getTokenBearer()).getBody();
		System.out.println(response);
	}

	@Override
	public void delete(Long id) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/books/delete/{id}");
		String uri = template.expand(id).toString();
		String response = (String) restService.DELETE(uri, params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
		System.out.println(response);
	}
}
