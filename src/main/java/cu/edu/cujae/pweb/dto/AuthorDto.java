package cu.edu.cujae.pweb.dto;

import java.util.ArrayList;
import java.util.List;

public class AuthorDto {
	private Long authorId;
	private String name;


	public AuthorDto(String name, Long authorId) {
		this.authorId = authorId;
		setName(name);
//		setBooks(new ArrayList<BookDto>());
	}

	public AuthorDto() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<BookDto> getBooks() {
//		return books;
//	}
//
//	public void setBooks(List<BookDto> books) {
//		this.books = books;
//	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

}

