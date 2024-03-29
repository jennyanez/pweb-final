package cu.edu.cujae.pweb.bean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import cu.edu.cujae.pweb.dto.BookDto;
import cu.edu.cujae.pweb.dto.ClientDto;
import cu.edu.cujae.pweb.dto.CopyDto;

@ManagedBean
public class LoanBean{
    private BookDto book;
	private CopyDto copy;
	private ClientDto user;
	private Date returnDate;
	private Date loanDate;
	private boolean isDefaulter;

    protected HttpServletRequest getRequest() {
	    return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
	}
	
	protected FacesContext getFacesContext() {
	    return FacesContext.getCurrentInstance();
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

	public ClientDto getUser() {
		return user;
	}

	public void setUser(ClientDto user) {
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

	public boolean isDefaulter() {
		return isDefaulter;
	}

	public void setDefaulter(boolean isDefaulter) {
		this.isDefaulter = isDefaulter;
	}	
}