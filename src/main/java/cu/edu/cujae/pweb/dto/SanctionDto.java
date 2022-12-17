package cu.edu.cujae.pweb.dto;

import java.util.Date;

public class SanctionDto {
    private Long sanctionId;
    private String typeOfSanction;
    private Date dateStartSanction;
    private Date dateEndSanction;
    private ClientDto client;

    public SanctionDto(Long sanctionId, String typeOfSanction, Date dateStartSanction, Date dateEndSanction, ClientDto client) {
        this.sanctionId = sanctionId;
        this.typeOfSanction = typeOfSanction;
        this.dateStartSanction = dateStartSanction;
        this.dateEndSanction = dateEndSanction;
        this.client = client;
    }

    public SanctionDto() {
    }

    /********** GETTERS AND SETTERS **********/
    public Long getSanctionId() {
        return sanctionId;
    }

    public void setSanctionId(Long sanctionId) {
        this.sanctionId = sanctionId;
    }

    public String getTypeOfSanction() {
        return typeOfSanction;
    }

    public void setTypeOfSanction(String typeOfSanction) {
        this.typeOfSanction = typeOfSanction;
    }

    public Date getDateStartSanction() {
        return dateStartSanction;
    }

    public void setDateStartSanction(Date dateStartSanction) {
        this.dateStartSanction = dateStartSanction;
    }

    public Date getDateEndSanction() {
        return dateEndSanction;
    }

    public void setDateEndSanction(Date dateEndSanction) {
        this.dateEndSanction = dateEndSanction;
    }

    public ClientDto getClient() {
        return client;
    }

    public void setClient(ClientDto client) {
        this.client = client;
    }
}
