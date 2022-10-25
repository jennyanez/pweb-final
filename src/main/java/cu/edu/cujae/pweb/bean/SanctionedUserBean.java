package cu.edu.cujae.pweb.bean;

import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
public class SanctionedUserBean{
	private int typeOfSanction;
	private Calendar dateStartSanction;
	private Calendar dateEndSanction;
	private String userFullName;
	private String userDni;

    protected HttpServletRequest getRequest() {
	    return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
	}
	
	protected FacesContext getFacesContext() {
	    return FacesContext.getCurrentInstance();
	}

	public int getTypeOfSanction() {
		return typeOfSanction;
	}

	public void setTypeOfSanction(int typeOfSanction) {
		this.typeOfSanction = typeOfSanction;
	}

	public Calendar getDateStartSanction() {
		return dateStartSanction;
	}

	public void setDateStartSanction(Calendar dateStartSanction) {
		this.dateStartSanction = dateStartSanction;
	}

	public Calendar getDateEndSanction() {
		return dateEndSanction;
	}

	public void setDateEndSanction(Calendar dateEndSanction) {
		this.dateEndSanction = dateEndSanction;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUserDni() {
		return userDni;
	}

	public void setUserDni(String userDni) {
		this.userDni = userDni;
	}

}