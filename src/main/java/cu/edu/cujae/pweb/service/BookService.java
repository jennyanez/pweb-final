package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.BookDto;

public interface BookService {
	List<BookDto> getBooks();
	BookDto getBookByCode(String code);
	void createBook(BookDto book);
	void updateBook(BookDto book);
	void deleteBook(String code);
}
