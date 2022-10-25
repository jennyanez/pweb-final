package cu.edu.cujae.pweb.dto;

import java.util.Date;

public class LoanRequestDto {
	private String id;
	private String user;
	private String book;
	private int copy;
	private Date loanRequestDate;
	private boolean newRecord;
	
	public LoanRequestDto(String id, String user, String book, int copy, Date loanRequest, boolean newRecord) {
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
