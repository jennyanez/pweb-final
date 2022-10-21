package dto;

import java.util.ArrayList;
import java.util.List;


public class MatterDto {
	private String name;
	private List<BookDto> books;
	
	public MatterDto(String name){
		setName(name);
		books = new ArrayList<BookDto>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BookDto> getBooks() {
		return books;
	}
}
