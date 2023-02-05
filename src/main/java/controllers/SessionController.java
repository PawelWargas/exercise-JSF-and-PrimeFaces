/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controllers;

import java.io.Serializable;
import javax.inject.Named;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Pawel
 */
@Named(value = "sessionController")
@SessionScoped
public class SessionController implements Serializable {
    
    private String sesID;
    @Inject
    ApplicationController applicationController;
    
    public SessionController() {
        javax.faces.context.ExternalContext externalContext2 = javax.faces.context.FacesContext.getCurrentInstance().getExternalContext();
        sesID = externalContext2.getSessionId(true);
        
    }

    public String getSesID() {
        return sesID;
    }

    public void setSesID(String sesID) {
        this.sesID = sesID;
    }

    public String getInfo(){
        return "Wersja aplikacji: "+applicationController.getAppVersion()+" Data: "+applicationController.getrDate()+" Id sesji: "+sesID;
    }

    public ApplicationController getApplicationController() {
        return applicationController;
    }

    public void setApplicationController(ApplicationController applicationController) {
        this.applicationController = applicationController;
    }
    
    
   
}
