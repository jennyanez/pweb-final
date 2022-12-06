package cu.edu.cujae.pweb.dto;

public class DefaulterClientDto extends ClientDto{
    private Long defaulterClientId;
    private String bookTitlePossession;
    private int amountDaysBreach;

    public DefaulterClientDto(Long clientId, String DNI, String area, String name, String firstSurname, String lastSurname) {
        super(clientId, DNI, area, name, firstSurname, lastSurname);
        // TODO Auto-generated constructor stub
        setBookTitlePossession(bookTitlePossession);
        setAmountDaysBreach(amountDaysBreach);
    }

    public DefaulterClientDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Long getDefaulterClientId() {
        return defaulterClientId;
    }

    public void setDefaulterClientId(Long defaulterClientId) {
        this.defaulterClientId = defaulterClientId;
    }

    public String getBookTitlePossession() {
        return bookTitlePossession;
    }

    public void setBookTitlePossession(String bookTitlePossession) {
        this.bookTitlePossession = bookTitlePossession;
    }

    public int getAmountDaysBreach() {
        return amountDaysBreach;
    }

    public void setAmountDaysBreach(int amountDaysBreach) {
        this.amountDaysBreach = amountDaysBreach;
    }
}
