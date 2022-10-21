package dto;

public class DefaulterUserDto extends UserDto {
	private String bookTitlePossession;
	private int amountDaysBreach;
	
	public DefaulterUserDto(String DNI, String area, String name, String firstSurname, String lastSurname, String bookTitlePossession,
			                int amountDaysBreach) {
		super(DNI, area, name, firstSurname, lastSurname);
		setBookTitlePossession(bookTitlePossession);
		setAmountDaysBreach(amountDaysBreach);
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
