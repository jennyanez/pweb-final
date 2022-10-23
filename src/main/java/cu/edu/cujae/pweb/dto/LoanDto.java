package cu.edu.cujae.pweb.dto;

import java.sql.Date;

public class LoanDto {
	private BookDto book;
	private CopyDto copy;
	private UserDto user;
	private Date returnDate;
	private Date loanDate;
	
	public LoanDto(BookDto book, CopyDto copy, UserDto user, Date returnDate, Date loanDate) {
		super();
		this.book = book;
		this.copy = copy;
		this.user = user;
		this.setReturnDate(returnDate);
		this.setLoanDate(loanDate);		
	}

	public BookDto getBook() {
		return book;
	}

	public void setBook(BookDto book) {
		this.book = book;
	}

	public CopyDto getCopy() {
		return copy;
	}

	public void setCopy(CopyDto copy) {
		this.copy = copy;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}	
}
