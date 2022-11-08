package cu.edu.cujae.pweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.AuthorDto;

@Service
public class AuthorServiceImpl implements AuthorService{
	
	@Override
	public List<AuthorDto> getAuthors(){
		
		List<AuthorDto> authors = new ArrayList<>();
		authors.add(new AuthorDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Andy", "Piloto","Guevara", true));
		authors.add(new AuthorDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Jenny", "Yanez","Piloto", true));
        authors.add(new AuthorDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Ana", "Gonzalez","Piloto", true));
		
		return authors;
	}
	
	@Override
	public AuthorDto getAuthorById(String AuthorId) {
		return getAuthors().stream().filter(r -> r.getId().equals(AuthorId)).findFirst().get();
	}
	
	@Override
	
	public void createAuthor(AuthorDto author) {
		
	}
	
	@Override
	public void updateAuthor(AuthorDto author) {
		
	}
	
	@Override
	public void deleteAuthor(String id) {
		
	}
}
