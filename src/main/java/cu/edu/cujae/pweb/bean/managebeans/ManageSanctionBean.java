package cu.edu.cujae.pweb.bean.managebeans;

import cu.edu.cujae.pweb.dto.ClientDto;
import cu.edu.cujae.pweb.dto.SanctionDto;
import cu.edu.cujae.pweb.service.ClientService;
import cu.edu.cujae.pweb.service.SanctionService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class ManageSanctionBean {
    private SanctionDto sanctionDto;
    private SanctionDto selectedSanction;
    private List<SanctionDto> sanctions;
    private List<ClientDto> clients;

    /**** Services ****/
    @Autowired
    private ClientService clientService;
    @Autowired
    private SanctionService sanctionService;

    public void openNew() {
        selectedSanction = new SanctionDto();
    }
    public void openForEdit(){

    }

    public void deleteSanction(){
        try{
            sanctionService.delete(this.selectedSanction.getSanctionId());
            this.selectedSanction = null;
            sanctions = sanctionService.getAll();
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_sanction_deleted");
            PrimeFaces.current().ajax().update("form:dt-sanctioned");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form

        }catch (Exception e){
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");

        }
    }

    /****** Constructor, Getters and Setters ******/
    public ManageSanctionBean() {
    }

    public SanctionDto getSanctionDto() {
        return sanctionDto;
    }

    public void setSanctionDto(SanctionDto sanctionDto) {
        this.sanctionDto = sanctionDto;
    }

    public SanctionDto getSelectedSanction() {
        return selectedSanction;
    }

    public void setSelectedSanction(SanctionDto selectedSanction) {
        this.selectedSanction = selectedSanction;
    }

    public List<SanctionDto> getSanctions() {
        sanctions = sanctionService.getAll();
        return sanctions;
    }

    public void setSanctions(List<SanctionDto> sanctions) {
        this.sanctions = sanctions;
    }

    public List<ClientDto> getClients() {
        clients = clientService.getAll();
        return clients;
    }

    public void setClients(List<ClientDto> clients) {
        this.clients = clients;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    public SanctionService getSanctionService() {
        return sanctionService;
    }

    public void setSanctionService(SanctionService sanctionService) {
        this.sanctionService = sanctionService;
    }
}
