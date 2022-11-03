package cu.edu.cujae.pweb.dto;

import java.util.ArrayList;
import java.util.List;

public class ClientDto {
	private String DNI;
	private String area;
	private String name;
	private String firstSurname;
	private String lastSurname;
	private String fullName;
	private List<CopyDto> copies;
	private boolean newRecord;
	
	public ClientDto(String DNI, String area, String name, String firstSurname, String lastSurname){
		setArea(area);
		setDNI(DNI);
		setName(name);
		setFirstSurname(firstSurname);
		setLastSurname(lastSurname);
		setFullName();
		copies = new ArrayList<CopyDto>();
	}

	public ClientDto() {
		// TODO Auto-generated constructor stub
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName() {
		fullName = getName() + " " + getFirstSurname() + " " + getLastSurname();
	}

	public List<CopyDto> getCopies() {
		return copies;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public boolean isNewRecord() {
		return newRecord;
	}

	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}
}
