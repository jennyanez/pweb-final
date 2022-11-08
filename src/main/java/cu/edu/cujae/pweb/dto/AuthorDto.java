package cu.edu.cujae.pweb.dto;

import java.util.ArrayList;
import java.util.List;

public class AuthorDto {
	private String id;
	private String name;
	private String firstSurname;
	private String secondSurname;
	private String fullName;
	private List<BookDto> books;
	public boolean newRecord;
	
	public AuthorDto(String id,String name, String firstSurname, String secondSurname, boolean newRecord){
		setId(id);
		setName(name);
		setFirstSurname(firstSurname);
		setSecondSurname(secondSurname);
		setFullName();
		setBooks(new ArrayList<BookDto>());
		setNewRecord(newRecord);
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

	public String getFirstSurname() {
		if(firstSurname == null)
			return "";
		
		return firstSurname;
	}

	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}

	public String getSecondSurname() {
		if(secondSurname == null)
			return "";
		
		return secondSurname;
	}

	public void setSecondSurname(String secondSurname) {
		this.secondSurname = secondSurname;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName() {
		fullName = getName() + " " + getFirstSurname() + " " + getSecondSurname();
	}

	public List<BookDto> getBooks() {
		return books;
	}

	public void setBooks(List<BookDto> books) {
		this.books = books;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
	
	public boolean getNewRecord() {
		return newRecord;
	}

	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}
}
