package cu.edu.cujae.pweb.dto;

public class DefaulterUserDto extends UserDto {
	private String bookTitlePossession;
	private int amountDaysBreach;
	
	public DefaulterUserDto(String DNI, String area, String name, String firstSurname, String lastSurname) {
		super(DNI, area, name, firstSurname, lastSurname);
		// TODO Auto-generated constructor stub
	}	

	public String getBookTitlePossession() {
		return bookTitlePossession;
	}

	public void setBookTitlePossession(String bookTitlePossession) {
		this.bookTitlePossession = bookTitlePossession;
	}

	public int getAmountDaysBreach() {
		return amountDaysBreach;
	}

	public void setAmountDaysBreach(int amountDaysBreach) {
		this.amountDaysBreach = amountDaysBreach;
	}
	
}
