package dto;

import java.util.List;

public class WebUserDto {
	private String id;
	private String username;
	private String fullName;
	private String password;
	private String email;
	private String identification;
	private boolean newRecord;
	private List<WebRoleDto> roles;
	
	public WebUserDto() {
		super();
	}

	public WebUserDto(String id, String username, String fullName, String password, String email, String identification,
			List<WebRoleDto> roles, boolean newRecord) {
		super();
		this.id = id;
		this.username = username;
		this.fullName = fullName;
		this.password = password;
		this.email = email;
		this.identification = identification;
		this.roles = roles;
		this.newRecord = newRecord;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public List<WebRoleDto> getRoles() {
		return roles;
	}
	public void setRoles(List<WebRoleDto> roles) {
		this.roles = roles;
	}

	public boolean isNewRecord() {
		return newRecord;
	}

	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}
	
}
