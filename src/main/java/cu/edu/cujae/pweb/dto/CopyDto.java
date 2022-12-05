package cu.edu.cujae.pweb.dto;

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
	public int getCopyId() {
		return copyId;
	}

	public void setCopyId(int copyId) {
		this.copyId = copyId;
	}
}
