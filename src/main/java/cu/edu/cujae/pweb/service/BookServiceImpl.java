package cu.edu.cujae.pweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.BookDto;

@Service
public class BookServiceImpl implements BookService{


	@Override
	public List<BookDto> getBooks() {
		return null;
	}

	@Override
	public BookDto getBookById(Long bookId) {
		return null;
	}

	@Override
	public void createBook(BookDto book) {

	}

	@Override
	public void updateBook(BookDto book) {

	}

	@Override
	public void deleteBook(Long code) {

	}
}
