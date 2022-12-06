package cu.edu.cujae.pweb.dto;

public class ClientDto {

	private Long clientId;
	private String DNI;
	private String area;
	private String name;
	private String firstSurname;
	private String lastSurname;
	
	public ClientDto(Long clientId, String DNI, String area, String name, String firstSurname, String lastSurname){
		setClientId(clientId);
		setArea(area);
		setDNI(DNI);
		setName(name);
		setFirstSurname(firstSurname);
		setLastSurname(lastSurname);
	}

	public ClientDto() {
		// TODO Auto-generated constructor stub
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFullName() {
		return  name  + firstSurname + lastSurname;
	}


}
