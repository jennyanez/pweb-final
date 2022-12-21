package cu.edu.cujae.pweb.bean.managebeans;

import cu.edu.cujae.pweb.dto.ClientDto;
import cu.edu.cujae.pweb.service.ClientService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped
public class ManageClientBean {
    private ClientDto clientDto;
    private ClientDto selectedClient;
    private List<ClientDto> clients;

    @Autowired
    private ClientService clientService;


    public void openNew(){
        this.selectedClient = new ClientDto();
    }

    public void openForEdit(ClientDto client){
        this.selectedClient = client;
    }

    public void saveClient() {
        String msg = "";
        if (this.selectedClient.getClientId() == null) {

            msg = clientService.create(this.selectedClient);

            if(!msg.isEmpty()){
                JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_client_already_exists");
            }else{
                JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_client_added");
            }
        }
        else {
            msg = clientService.update(this.selectedClient);
            if(!msg.isEmpty()) {
                JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_client_already_exists");

            }else{
                JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_client_edited");
            }
        }
        clients = clientService.getAll();
        PrimeFaces.current().executeScript("PF('manageClientDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-client");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    }

    public void deleteClient() {
        try {
            String msg = clientService.delete(this.selectedClient.getClientId());
            this.selectedClient = null;
            clients = clientService.getAll();
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_client_removed");
            PrimeFaces.current().ajax().update("form:dt-client");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
        } catch (Exception e) {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
        }
    }

    /**** CONSTRUCTOR , GETTERS AND SETTERS ******/
    public ManageClientBean() {

    }

    public ClientDto getClientDto() {
        return clientDto;
    }

    public void setClientDto(ClientDto clientDto) {
        this.clientDto = clientDto;
    }

    public ClientDto getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(ClientDto selectedClient) {
        this.selectedClient = selectedClient;
    }

    public List<ClientDto> getClients() {
    	clients = clientService.getAll();
        return clients;
    }

    public void setClients(List<ClientDto> clients) {
        this.clients = clients;
    }
}
