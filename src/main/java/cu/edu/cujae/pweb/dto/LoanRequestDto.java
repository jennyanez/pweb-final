package cu.edu.cujae.pweb.dto;

import java.sql.Date;

public class LoanRequestDto {
	private String user;
	private String book;
	private int copy;
	private Date loanRequest;
	private boolean newRecord;
	
	public LoanRequestDto(String user, String book, int copy, Date loanRequest, boolean newRecord) {
		super();
		this.user = user;
		this.book = book;
		this.copy = copy;
		this.loanRequest = loanRequest;
		this.setNewRecord(newRecord);
	}
	
	public LoanRequestDto() {
		// TODO Auto-generated constructor stub
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public int getCopy() {
		return copy;
	}
	public void setCopy(int copy) {
		this.copy = copy;
	}
	public Date getLoanRequest() {
		return loanRequest;
	}
	public void setLoanRequest(Date loanRequest) {
		this.loanRequest = loanRequest;
	}

	public boolean getNewRecord() {
		return newRecord;
	}

	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}
}
