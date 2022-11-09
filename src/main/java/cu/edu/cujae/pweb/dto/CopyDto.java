package cu.edu.cujae.pweb.dto;

public class CopyDto extends BookDto {
	private String copyId;
	private int copyNumber;

	public CopyDto(String bookId, String code, String title, AuthorDto authors, MatterDto matter, boolean newRecord, String copyId,int copyNumber){
		super(bookId,code,title,authors,matter,newRecord);
		setCopyNumber(copyNumber);
		setCopyId(copyId);
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

	public String getCopyId() {
		return copyId;
	}

	public void setCopyId(String copyId) {
		this.copyId = copyId;
	}
}
