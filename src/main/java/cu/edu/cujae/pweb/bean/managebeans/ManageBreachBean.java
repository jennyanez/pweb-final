package cu.edu.cujae.pweb.bean.managebeans;

import cu.edu.cujae.pweb.dto.BreachDto;
import cu.edu.cujae.pweb.dto.ClientDto;
import cu.edu.cujae.pweb.dto.CopyDto;
import cu.edu.cujae.pweb.service.BreachService;
import cu.edu.cujae.pweb.service.ClientService;
import cu.edu.cujae.pweb.service.CopyService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class ManageBreachBean {
    private BreachDto breachDto;
    private BreachDto selectedBreach;
    private List<CopyDto> copies;
    private List<ClientDto> clients;
    private List<BreachDto> breaches;

    /**** Services ****/
    @Autowired
    private CopyService copyService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BreachService breachService;

    @PostConstruct
    public void init() {
        copies = copyService.getAll();
        clients = clientService.getAll();
        breaches = breachService.getAll();
    }

    public void openNew() {
        selectedBreach = new BreachDto();
    }

    public void openForEdit(){

    }

    public void deleteBreach(){
        try{
            breachService.delete(this.selectedBreach.getBreachId());
            this.selectedBreach = null;
            breaches = breachService.getAll();
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_breach_deleted");
            PrimeFaces.current().ajax().update("form:dt-breach");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
        }catch (Exception e){
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
        }

    }

    /***** Constructor ******/
    public ManageBreachBean() {
    }

    /******* Getters and setters ********/
    public BreachDto getSelectedBreach() {
        return selectedBreach;
    }

    public void setSelectedBreach(BreachDto selectedBreach) {
        this.selectedBreach = selectedBreach;
    }

    public List<CopyDto> getCopies() {
        return copies;
    }

    public void setCopies(List<CopyDto> copies) {
        this.copies = copies;
    }

    public List<ClientDto> getClients() {
        return clients;
    }

    public void setClients(List<ClientDto> clients) {
        this.clients = clients;
    }

    public List<BreachDto> getBreaches() {
        return breaches;
    }

    public void setBreaches(List<BreachDto> breaches) {
        this.breaches = breaches;
    }

    public BreachDto getBreachDto() {
        return breachDto;
    }

    public void setBreachDto(BreachDto breachDto) {
        this.breachDto = breachDto;
    }
}
