package cu.edu.cujae.pweb.bean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
public class LoanRequestBean {
	 	private String book;
		private int copy;
		private String user;
		private Date loanDate;

	    protected HttpServletRequest getRequest() {
		    return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
		}
		
		protected FacesContext getFacesContext() {
		    return FacesContext.getCurrentInstance();
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

		public Date getLoanDate() {
			return loanDate;
		}

		public void setLoanDate(Date loanDate) {
			this.loanDate = loanDate;
		}

	    	
}
