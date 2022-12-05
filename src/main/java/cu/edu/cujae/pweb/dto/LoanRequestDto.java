package cu.edu.cujae.pweb.dto;


import java.util.Date;
public class LoanRequestDto {
	private Long id;
	private ClientDto client;
	private BookDto book;
	private CopyDto copy;
	private Date loanRequestDate;

	public LoanRequestDto(Long id, ClientDto client, BookDto book, CopyDto copy, Date loanRequestDate) {
		this.id = id;
		this.client = client;
		this.book = book;
		this.copy = copy;
		this.loanRequestDate = loanRequestDate;
	}

	public LoanRequestDto() {
	}

	/*************  Getters  And  Setters   ***************/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClientDto getClient() {
		return client;
	}

	public void setClient(ClientDto client) {
		this.client = client;
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

	public void setLoanRequestDate(Date loanRequestDate) {
		this.loanRequestDate = loanRequestDate;
	}
}
