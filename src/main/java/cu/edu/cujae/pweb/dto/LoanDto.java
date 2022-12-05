package cu.edu.cujae.pweb.dto;

import java.util.Date;

public class LoanDto {
	private Long id;
	private CopyDto copy;
	private ClientDto client;
	private Date returnDate;
	private Date loanDate;
	
	public LoanDto(Long loanId, CopyDto copy, ClientDto client, Date returnDate, Date loanDate) {
		super();
		this.id = loanId;
		this.copy = copy;
		this.client = client;
		this.setReturnDate(returnDate);
		this.setLoanDate(loanDate);
	}
	
	public LoanDto() {
		// TODO Auto-generated constructor stub
	}

	/******************      Getters  And  Setters      ************************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CopyDto getCopy() {
		return copy;
	}

	public void setCopy(CopyDto copy) {
		this.copy = copy;
	}

	public ClientDto getClient() {
		return client;
	}

	public void setClient(ClientDto client) {
		this.client = client;
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
