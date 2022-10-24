package cu.edu.cujae.pweb.dto;

public class BookDto {
	private String bookId;
	private String code;
	private String title;
	private int yearEdition;
	private String editorial;
	private String countryOrigin;
	private String summary;
	private int amountPages;
	private String matter;
	private String authors;
	private boolean newRecord;
	
	public BookDto(String bookId, String title, String authors, String matter, boolean newRecord){
		setBookId(bookId);
		setTitle(title);
		setAuthors(authors);
		setMatter(matter);
		setNewRecord(newRecord);
	}
	
	public BookDto(String bookId, String code, String title, String authors, String matter, boolean newRecord){
		setBookId(bookId);
		setCode(code);
		setTitle(title);
		setAuthors(authors);
		setMatter(matter);
		setNewRecord(newRecord);
	}
	
	/**
	 * @return the newRecord
	 */
	public boolean isNewRecord() {
		return newRecord;
	}

	/**
	 * @param newRecord the newRecord to set
	 */
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}
	
	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public BookDto() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYearEdition() {
		return yearEdition;
	}

	public void setYearEdition(int yearEdition) {
		this.yearEdition = yearEdition;
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

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
