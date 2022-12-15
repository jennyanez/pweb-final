package cu.edu.cujae.pweb.bean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import cu.edu.cujae.pweb.dto.BookDto;
import cu.edu.cujae.pweb.dto.ClientDto;
import cu.edu.cujae.pweb.dto.CopyDto;

@ManagedBean
public class LoanRequestBean {
	
		private String bookTitle;
		private String client;
		private Date loanDate;

	    protected HttpServletRequest getRequest() {
		    return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
		}
		
		protected FacesContext getFacesContext() {
		    return FacesContext.getCurrentInstance();
		}

		public String getBookTitle() {
			return bookTitle;
		}

		public void setBookTitle(String bookTitle) {
			this.bookTitle = bookTitle;
		}

		public String getClient() {
			return client;
		}

		public void setClient(String client) {
			this.client = client;
		}

		public Date getLoanDate() {
			return loanDate;
		}

		public void setLoanDate(Date loanDate) {
			this.loanDate = loanDate;
		}

		 	
}
