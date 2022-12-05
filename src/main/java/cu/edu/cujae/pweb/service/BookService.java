package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.BookDto;

public interface BookService {
	List<BookDto> getBooks();
	BookDto getBookById(Long bookId);
	void createBook(BookDto book);
	void updateBook(BookDto book);
	void deleteBook(Long code);
	
	//comentario
}
