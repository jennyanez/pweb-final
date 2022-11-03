package cu.edu.cujae.pweb.bean;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import cu.edu.cujae.pweb.dto.CopyDto;

public class ClientBean {
	private String DNI;
	private String area;
	private String name;
	private String firstSurname;
	private String lastSurname;
	private List<CopyDto> copies;
	
	protected HttpServletRequest getRequest() {
	    return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
	}
	
	protected FacesContext getFacesContext() {
	    return FacesContext.getCurrentInstance();
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFirstSurname() {
		return firstSurname;
	}

	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}

	public String getLastSurname() {
		return lastSurname;
	}

	public void setLastSurname(String lastSurname) {
		this.lastSurname = lastSurname;
	}

	public List<CopyDto> getCopies() {
		return copies;
	}

	public void setCopies(List<CopyDto> copies) {
		this.copies = copies;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
