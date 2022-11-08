package cu.edu.cujae.pweb.dto;

import java.util.Date;

public class LoanRequestDto {
	private String id;
	private ClientDto user;
	private BookDto book;
	private CopyDto copy;
	private Date loanRequestDate;
	private boolean newRecord;
	
	public LoanRequestDto(String id, ClientDto user, BookDto book, CopyDto copy, Date loanRequest, boolean newRecord) {
		super();
		this.id = id;
		this.user = user;
		this.book = book;
		this.copy = copy;
		this.loanRequestDate = loanRequest;
		this.setNewRecord(newRecord);
	}
	
	public LoanRequestDto() {
		// TODO Auto-generated constructor stub
	}
	
	public ClientDto getUser() {
		return user;
	}
	public void setUser(ClientDto user) {
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
	public Date getLoanRequestDate() {
		return loanRequestDate;
	}
	public void setLoanRequestDate(Date loanRequest) {
		this.loanRequestDate = loanRequest;
	}

	public boolean getNewRecord() {
		return newRecord;
	}

	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
