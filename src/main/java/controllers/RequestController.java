/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controllers;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Pawel
 */
@Named(value = "requestController")
@Dependent
public class RequestController {

    /**
     * Creates a new instance of RequestController
     */
    public RequestController() {
    }
    
     public String logout(){
        javax.faces.context.ExternalContext externalContext = javax.faces.context.FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        return "/logout/logoutWindow.xhtml?faces-redirect=true";
        
    }
    public String mainSite(){
        return "/index.xhtml?faces-redirect=true";
        
    }
    
    public String logonSite(){
        return "/logon/logonWindow.html?faces-redirect=true";
        
    }
}
