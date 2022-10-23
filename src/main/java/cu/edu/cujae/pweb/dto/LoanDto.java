package cu.edu.cujae.pweb.dto;

import java.util.Date;

public class LoanDto {
	private String id;
	private String book;
	private int copy;
	private String user;
	private Date returnDate;
	private Date loanDate;
	
	public LoanDto(String loanId, String book, int copy, String user, Date returnDate, Date loanDate) {
		super();
		this.id = loanId;
		this.book = book;
		this.copy = copy;
		this.user = user;
		this.setReturnDate(returnDate);
		this.setLoanDate(loanDate);		
	}
	
	public LoanDto() {
		// TODO Auto-generated constructor stub
	}

	public void setLoanId(String loanId){
		this.id = loanId;
	}

	public String getLoanId(){
		return this.id;
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
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
