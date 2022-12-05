package cu.edu.cujae.pweb.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cu.edu.cujae.pweb.service.BookService;
import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.BookDto;

@Service
public class BookServiceImpl implements BookService {
	
	@Override
	public List<BookDto> getBooks() {
		
		List<BookDto> books = new ArrayList<>();
//		books.add(new BookDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9),"123411a31", "The Great Gatsby", "F.Scott Fitzgerald", "Literature", true));
//		books.add(new BookDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9),"8919d2282", "Pride and Perjuice", "Jane Austen", "Classic",true));
//        books.add(new BookDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9),"13211233h", "The Picture of Dorian Gray", "Oscar Wilde", "Literature",true));

		return books;
	}

	@Override
	public BookDto getBookById(Long bookId) {
		return getBooks().stream().filter(r -> r.getBookId().equals(bookId)).findFirst().get();
	}
	//comentario
	@Override
	public void createBook(BookDto book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBook(BookDto book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBook(Long code) {
		// TODO Auto-generated method stub
		
	}

}
