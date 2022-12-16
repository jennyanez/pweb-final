package cu.edu.cujae.pweb.dto;

public class BreachDto {
    private Long breachId;
    private ClientDto client;
    private CopyDto copy;
    private int days;

    /*************** Constructors *******************/
    public BreachDto(Long breachId, ClientDto client, CopyDto copy, int days) {
        this.breachId = breachId;
        this.client = client;
        this.copy = copy;
        this.days = days;
    }

    public BreachDto() {
    }

    /**************** Getters and setters ******************************/
    public Long getBreachId() {
        return breachId;
    }

    public void setBreachId(Long breachId) {
        this.breachId = breachId;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }

    public CopyDto getCopy() {
        return copy;
    }

    public void setCopy(CopyDto copy) {
        this.copy = copy;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
