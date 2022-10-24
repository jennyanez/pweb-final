package cu.edu.cujae.pweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.BookDto;

@Service
public class BookServiceImpl implements BookService{
	
	@Override
	public List<BookDto> getBooks() {
		
		List<BookDto> books = new ArrayList<>();
		books.add(new BookDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "The Great Gatsby", "F.Scott Fitzgerald", "Literature", false));
		books.add(new BookDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Pride and Perjuice", "Jane Austen", "Classic",false));
        books.add(new BookDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "The Picture of Dorian Gray", "Oscar Wilde", "Literature",false));

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
