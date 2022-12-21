package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.pweb.dto.AuthorDto;
import cu.edu.cujae.pweb.dto.BookDto;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.IAuthorService;
import cu.edu.cujae.pweb.utils.RestService;
import cu.edu.cujae.pweb.utils.ServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

@Service
public class AuthorService implements ServiceImplementation,IAuthorService {
	@Autowired
	private RestService restService;

	@Override
	public List<AuthorDto> getAll() {
		List<AuthorDto> authorDtoList = new ArrayList<>();
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<AuthorDto> apiRestMapper = new ApiRestMapper<>();
			String response = (String)restService.GET("/api/v1/authors/all", params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
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

			UriTemplate template = new UriTemplate("/api/v1/authors/{id}");
			String uri = template.expand(id).toString();
			String response = (String)restService.GET(uri, params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
			authorDto = apiRestMapper.mapOne(response, AuthorDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return authorDto;
	}

	@Override
	public void create(Object author) {
		AuthorDto authorDto = (AuthorDto) author;
		String response = (String) restService.POST("/api/v1/authors/save", authorDto, String.class,CurrentUserUtils.getTokenBearer()).getBody();
		System.out.println(response);
	}

	@Override
	public void update(Object author) {
		AuthorDto authorDto = (AuthorDto) author;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		String response = (String) restService.PUT("/api/v1/authors/update", params, authorDto, String.class,CurrentUserUtils.getTokenBearer()).getBody();
		System.out.println(response);
	}

	@Override
	public void delete(Long id) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/authors/delete/{id}");
		String uri = template.expand(id).toString();
		String response = (String) restService.DELETE(uri, params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
		System.out.println(response);
	}
	
	@Override
	public List<BookDto> getBookByAuthorId(Long idAuthor) {
		
		List<BookDto> bookList = new ArrayList<>();
		List<BookDto> bookListResult = new ArrayList<>();
		
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	        ApiRestMapper<BookDto> apiRestMapper = new ApiRestMapper<>();
	        String response = (String)restService.GET("/api/v1/books/all", params, String.class,CurrentUserUtils.getTokenBearer()).getBody();
	        bookList = apiRestMapper.mapList(response, BookDto.class);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0;i<bookList.size();i++) {
			for(int f = 0;f<bookList.get(i).getAuthors().size();f++) {
				if(bookList.get(i).getAuthors().get(f).getAuthorId().equals(idAuthor)) {
					bookListResult.add(bookList.get(i));
				}
			}
		}		
		
		return bookListResult;
	}
}
