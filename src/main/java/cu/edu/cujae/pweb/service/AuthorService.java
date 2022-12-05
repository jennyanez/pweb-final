package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.AuthorDto;

public interface AuthorService {
	List<AuthorDto> getAuthors();
	AuthorDto getAuthorById(String AuthorId);
	void createAuthor(AuthorDto author);
	void updateAuthor(AuthorDto author);
	void deleteAuthor(String id);
}
