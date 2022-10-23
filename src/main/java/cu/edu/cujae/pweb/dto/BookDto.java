package cu.edu.cujae.pweb.dto;

import java.util.ArrayList;
import java.util.List;

public class BookDto {
	private String code;
	private String title;
	private int yearEdition;
	private String editorial;
	private String countryOrigin;
	private String summary;
	private int amountPages;
	private MatterDto matter;
	private List<AuthorDto> authors;
	
	public BookDto(String code, String title, int yearEdition, String editorial, String countryOrigin,
			String summary, int amountPages, MatterDto matter){
		setCode(code);
		setTitle(title);
		setYearEdition(yearEdition);
		setEditorial(editorial);
		setCountryOrigin(countryOrigin);
		setSummary(summary);
		setAmountPages(amountPages);
		setMatter(matter);
		setAuthors(new ArrayList<AuthorDto>());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYearEdition() {
		return yearEdition;
	}

	public void setYearEdition(int yearEdition) {
		this.yearEdition = yearEdition;
	}

	public String getCountryOrigin() {
		return countryOrigin;
	}

	public void setCountryOrigin(String countryOrigin) {
		this.countryOrigin = countryOrigin;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getAmountPages() {
		return amountPages;
	}

	public void setAmountPages(int amountPages) {
		this.amountPages = amountPages;
	}

	public MatterDto getMatter() {
		return matter;
	}

	public void setMatter(MatterDto matter) {
		this.matter = matter;
	}

	public List<AuthorDto> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorDto> authors) {
		this.authors = authors;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
