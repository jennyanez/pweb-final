package dto;

public class XRoleDto {
	String rol;
	String description;
	
	public XRoleDto(String rol, String description) {
		setRol(rol);
		setDescription(description);
		
	}
	
	
	public String getRol() {
		return rol;
	}


	public void setRol(String rol) {
		this.rol = rol;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String toString()
	{
		return description;
	}
}

