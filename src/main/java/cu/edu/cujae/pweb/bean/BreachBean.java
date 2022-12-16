package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.ClientDto;
import cu.edu.cujae.pweb.dto.CopyDto;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
public class BreachBean {
    private String clientName;
    private String bookName;
    private String copyNumber;
    private int days;

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }


    /******** Constructors *********/
    public BreachBean(){

    }

    public BreachBean(String clientName, String bookName, String copyNumber, int days) {
        this.clientName = clientName;
        this.bookName = bookName;
        this.copyNumber = copyNumber;
        this.days = days;
    }

    /******* Getters and setters *********/
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(String copyNumber) {
        this.copyNumber = copyNumber;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
