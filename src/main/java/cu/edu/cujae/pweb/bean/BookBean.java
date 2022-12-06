package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.AuthorDto;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ManagedBean
public class BookBean {
	
	private String code;
	private String title;
	private String matter;
	private List<AuthorDto> authors;
	
	public BookBean() {
		// TODO Auto-generated constructor stub
	}

	public BookBean(String code, String title, String matter, List<AuthorDto> authors) {
		this.code = code;
		this.title = title;
		this.matter = matter;
		this.authors = authors;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}


	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public List<AuthorDto> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorDto> authors) {
		this.authors = authors;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getMatter() {
		return matter;
	}
	
	public void setMatter(String matter) {
		this.matter = matter;
	}
	


	
	protected HttpServletRequest getRequest() {
	    return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
	}
	
	protected FacesContext getFacesContext() {
	    return FacesContext.getCurrentInstance();
	}
	
	
}
