package cu.edu.cujae.pweb.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
public class SanctionBean {
    private String clientName;
    private String typeOfSanction;
    private String dateStartSanction;
    private String dateEndSanction;

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /******** Constructors *********/
    public SanctionBean(){

    }

    public SanctionBean(String clientName, String typeOfSanction, String dateStartSanction, String dateEndSanction) {
        this.clientName = clientName;
        this.typeOfSanction = typeOfSanction;
        this.dateStartSanction = dateStartSanction;
        this.dateEndSanction = dateEndSanction;
    }

    /******* Getters and setters *********/
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getTypeOfSanction() {
        return typeOfSanction;
    }

    public void setTypeOfSanction(String typeOfSanction) {
        this.typeOfSanction = typeOfSanction;
    }

    public String getDateStartSanction() {
        return dateStartSanction;
    }

    public void setDateStartSanction(String dateStartSanction) {
        this.dateStartSanction = dateStartSanction;
    }

    public String getDateEndSanction() {
        return dateEndSanction;
    }

    public void setDateEndSanction(String dateEndSanction) {
        this.dateEndSanction = dateEndSanction;
    }
}
