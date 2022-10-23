package cu.edu.cujae.pweb.dto;

public class CopyDto extends BookDto {
private int copyNumber;
	
	public CopyDto(String title, int yearEdition, String editorial, String countryOrigin, String summary, int amountPages, MatterDto matter, int copyNumber, String code){
		super(code, title, yearEdition, editorial, countryOrigin, summary, amountPages, matter);
		setCopyNumber(copyNumber);
	}

	public int getCopyNumber() {
		return copyNumber;
	}

	public void setCopyNumber(int copyNumber) {
		this.copyNumber = copyNumber;
	}
}
