package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.utils.JsfUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.util.Locale;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class LanguageBean {
    private Locale locale;
    private String lang;

    @PostConstruct
    public void init() {
        locale = JsfUtils.getCurrentLocale();

    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

//    public void setLanguage() {
//        locale = new Locale(this.lang);
//        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
//    }

    public void changeLang(ValueChangeEvent e) {
        Object newValue = e.getNewValue();
        this.lang = newValue.toString();
        Logger.getAnonymousLogger().severe("new lang ==> " + newValue.toString());
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        viewRoot.setLocale(new Locale(newValue.toString()));
    }

}
