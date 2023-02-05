/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controllers;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Pawel
 */
//@Named(value = "xMLController")
//@RequestScoped
public class XMLController {
    
    String myName;
    String group;
    
    public XMLController() {
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    
    
    
}
