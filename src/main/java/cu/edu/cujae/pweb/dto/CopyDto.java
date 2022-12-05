package cu.edu.cujae.pweb.dto;

<<<<<<< HEAD
public class CopyDto {

	private int copyNumber;
	private int copyId;

	public BookDto getBook() {
		return book;
	}

	public void setBook(BookDto book) {
		this.book = book;
	}

	private BookDto book;

	public CopyDto(int copyNumber, int copyId, BookDto book){
		setCopyNumber(copyNumber);
		setCopyId(copyId);
		this.book = book;
=======
public class CopyDto extends BookDto {
	private String copyId;
	private int copyNumber;

	public CopyDto(String bookId, String code, String title, AuthorDto authors, MatterDto matter, boolean newRecord, String copyId,int copyNumber){
		super(bookId,code,title,authors,matter,newRecord);
		setCopyNumber(copyNumber);
		setCopyId(copyId);
>>>>>>> 2c4b36bca87f6220cfd6768d23d320567387443d
	}

	public CopyDto() {
		// TODO Auto-generated constructor stub
	}

	public int getCopyNumber() {
		return copyNumber;
	}

	public void setCopyNumber(int copyNumber) {
		this.copyNumber = copyNumber;
	}
<<<<<<< HEAD
	public int getCopyId() {
		return copyId;
	}

	public void setCopyId(int copyId) {
=======

	public String getCopyId() {
		return copyId;
	}

	public void setCopyId(String copyId) {
>>>>>>> 2c4b36bca87f6220cfd6768d23d320567387443d
		this.copyId = copyId;
	}
}
