package cu.edu.cujae.pweb.dto;

import java.sql.Date;

public class LoanRequest {
	private UserDto user;
	private BookDto book;
	private CopyDto copy;
	private Date loanRequest;
	
	public LoanRequest(UserDto user, BookDto book, CopyDto copy, Date loanRequest) {
		super();
		this.user = user;
		this.book = book;
		this.copy = copy;
		this.loanRequest = loanRequest;
	}
	
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
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
	public Date getLoanRequest() {
		return loanRequest;
	}
	public void setLoanRequest(Date loanRequest) {
		this.loanRequest = loanRequest;
	}
}
