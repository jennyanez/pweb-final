package cu.edu.cujae.pweb.dto;

public class XRoleDto {
	private Long id;
	private String description;


	public XRoleDto(Long id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public XRoleDto() {
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
