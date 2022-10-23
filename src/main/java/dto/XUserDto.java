package dto;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class XUserDto {
	String username;
	String password;
	String rol;
	
	public XUserDto(String username, String rol) {
		super();
		this.username = username;
		this.rol = rol;
	}
	
	public XUserDto() {
		super();
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

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public String toString () {
		return username;
	}
}
