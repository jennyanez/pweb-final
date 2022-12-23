package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.LoanRequestDto;
import cu.edu.cujae.pweb.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@ManagedBean
@RequestScoped
public class LoanRequestConverter implements Converter {


    @Autowired
    LoanRequestService loanRequestService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        Long id;
        try{
            id = Long.parseLong(s);
        }catch (NumberFormatException | NullPointerException nfe){
            id = null;
        }

        LoanRequestDto loanRequestDto = loanRequestService.getById(id);

        return loanRequestDto;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        return null;
    }

}
