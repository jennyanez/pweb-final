package cu.edu.cujae.pweb.dto;

public class CopyDto {


	private int copyNumber;
	private Long copyId;
	private BookDto book;

	
	
	public CopyDto(int copyNumber, Long copyId, BookDto book){
		setCopyNumber(copyNumber);
		setCopyId(copyId);
		this.book = book;
	}

	public CopyDto() {
		
	}

	public int getCopyNumber() {
		return copyNumber;
	}

	public void setCopyNumber(int copyNumber) {
		this.copyNumber = copyNumber;
	}

	public Long getCopyId() {
		return copyId;
	}

	public void setCopyId(Long copyId) {
		this.copyId = copyId;
	}

	public BookDto getBook() {
		return book;
	}

	public void setBook(BookDto book) {
		this.book = book;
	}

	
}
