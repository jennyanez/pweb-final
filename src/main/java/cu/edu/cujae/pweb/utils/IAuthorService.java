package cu.edu.cujae.pweb.utils;

import java.util.List;

import cu.edu.cujae.pweb.dto.BookDto;

public interface IAuthorService {
	public List<BookDto> getBookByAuthorId(Long idAuthor);
}
