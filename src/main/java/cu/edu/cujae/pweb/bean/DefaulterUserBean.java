package cu.edu.cujae.pweb.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
public class DefaulterUserBean{
	private String bookTitlePossession;
	private int amountDaysBreach;
	private String userDni;
	private String userFullName;

    protected HttpServletRequest getRequest() {
	    return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
	}
	
	protected FacesContext getFacesContext() {
	    return FacesContext.getCurrentInstance();
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

	public String getUserDni() {
		return userDni;
	}

	public void setUserDni(String userDni) {
		this.userDni = userDni;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	
}