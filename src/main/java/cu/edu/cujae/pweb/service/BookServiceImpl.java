package cu.edu.cujae.pweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.BookDto;

@Service
public class BookServiceImpl implements BookService{
	
	@Override
	public List<BookDto> getBooks() {
		
		List<BookDto> books = new ArrayList<>();
		books.add(new BookDto("010234622234", "The Great Gatsby", "F.Scott Fitzgerald", "Literature"));
		books.add(new BookDto("033346675890", "Pride and Perjuice", "Jane Austen", "Classic"));
        books.add(new BookDto("045627782883", "The Picture of Dorian Gray", "Oscar Wilde", "Literature"));

		return books;
	}

	@Override
	public BookDto getBookByCode(String code) {
		return getBooks().stream().filter(r -> r.getCode().equals(code)).findFirst().get();
	}

	@Override
	public void createBook(BookDto book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBook(BookDto book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBook(String code) {
		// TODO Auto-generated method stub
		
	}

}
