package cu.edu.cujae.pweb.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
	private int code;
	private String username;
	private String password;
	private String email;
	private String recoverCode;
	private String firstName;
	private String lastName;
	private List<RoleDto> roles;
	private List<String> rolesName = new ArrayList<>();

	public UserDto(int code, String username, String password, String email, String recoverCode, String firstName, String lastName) {
		this.code = code;
		this.username = username;
		this.password = password;
		this.email = email;
		this.recoverCode = recoverCode;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public UserDto() {
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRecoverCode() {
		return recoverCode;
	}

	public void setRecoverCode(String recoverCode) {
		this.recoverCode = recoverCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

	public List<String> getRolesName() {
		return rolesName;
	}

	public void setRolesName(List<String> rolesName) {
		this.rolesName = rolesName;
	}

	public void rolesName(){
		for(RoleDto r : roles){
			rolesName.add(r.getRole());
		}
	}
}
