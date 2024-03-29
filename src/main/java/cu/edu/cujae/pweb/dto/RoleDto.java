package cu.edu.cujae.pweb.dto;

public class RoleDto {
	private int code;
	private String role;
	private String description;

	public RoleDto(int code, String role, String description) {
		this.code = code;
		this.role = role;
		this.description = description;
	}

	public RoleDto() {
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
