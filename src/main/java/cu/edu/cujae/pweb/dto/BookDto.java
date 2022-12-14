package cu.edu.cujae.pweb.dto;

import java.util.ArrayList;
import java.util.List;

public class BookDto {
	private Long bookId;
	private String bookCode;
	private String bookTitle;
	private int yearEdition;
	private String editorial;
	private String countryOrigin;
	private String summary;
	private int amountPages;
	private MatterDto matter;
	private List<AuthorDto> authors;

	private List<String> authorsName;

	public BookDto(Long bookId, String bookCode, String bookTitle, MatterDto matter, List<AuthorDto> authors){
		setBookId(bookId);
		setBookCode(bookCode);
		setBookTitle(bookTitle);
		setAuthors(authors);
		setMatter(matter);
	}
	public BookDto() {
	}

	public BookDto(Long bookId, String bookCode, String bookTitle, int yearEdition, String editorial, String countryOrigin, String summary, int amountPages, MatterDto matter, List<AuthorDto> authors) {
		this.bookId = bookId;
		this.bookCode = bookCode;
		this.bookTitle = bookTitle;
		this.yearEdition = yearEdition;
		this.editorial = editorial;
		this.countryOrigin = countryOrigin;
		this.summary = summary;
		this.amountPages = amountPages;
		this.matter = matter;
		this.authors = authors;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public int getYearEdition() {
		return yearEdition;
	}

	public void setYearEdition(int yearEdition) {
		this.yearEdition = yearEdition;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getCountryOrigin() {
		return countryOrigin;
	}

	public void setCountryOrigin(String countryOrigin) {
		this.countryOrigin = countryOrigin;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getAmountPages() {
		return amountPages;
	}

	public void setAmountPages(int amountPages) {
		this.amountPages = amountPages;
	}

	public MatterDto getMatter() {
		return matter;
	}

	public void setMatter(MatterDto matter) {
		this.matter = matter;
	}

	public List<AuthorDto> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorDto> authors) {
		this.authors = authors;
	}

	public List<String> getAuthorsName() {
		return authorsName;
	}

	public void authorsName() {
		authorsName = new ArrayList<>();
		for (AuthorDto a: authors){
			this.authorsName.add(a.getName());
		}
	}

	public void setAuthorsName(List<String> authorsName) {
		this.authorsName = authorsName;
	}
}
