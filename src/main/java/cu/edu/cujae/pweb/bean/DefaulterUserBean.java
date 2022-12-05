package cu.edu.cujae.pweb.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
public class DefaulterUserBean{
	private String bookTitlePossession;
	private int amountDaysBreach;
	private String clientName;
	//private XUserDto userName;

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

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

/*	public XUserDto getUserName() {
		return userName;
	}

	public void setUserName(XUserDto userName) {
		this.userName = userName;
	}
*/
	
}