package cu.edu.cujae.pweb.dto;

public class CopyDto extends BookDto {
private int copyNumber;
	
	public CopyDto(String title, String matter, String code, String author, boolean newRecord){
		super(code, title, author, matter, newRecord);
		setCopyNumber(copyNumber);
	}

	public int getCopyNumber() {
		return copyNumber;
	}

	public void setCopyNumber(int copyNumber) {
		this.copyNumber = copyNumber;
	}
}
