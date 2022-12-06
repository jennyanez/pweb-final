package cu.edu.cujae.pweb.dto;

public class XUserDto {
	private String username;
	private String fullName;
	private String password;
 	private String email;
	private String identification;
	private XRoleDto rol;
	
	public XUserDto() {
		super();
	}

	public XUserDto(String username, String password, XRoleDto rol,String fullName,
					String email,String identification) {
		this.username = username;
		this.password = password;
		this.rol = rol;
		this.fullName = fullName;
		this.email = email;
		this.identification = identification;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public XRoleDto getRol() {
		return rol;
	}

	public void setRol(XRoleDto rol) {
		this.rol = rol;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

//	public String generateUsername(String fullName){
//		String lower = fullName.toLowerCase();
//	//	lower = lower.trim();
//		char[] word = lower.toCharArray();
//		String first = lower.
//
//	}
}
