/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controllers;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import to.UserTO;


/**
 *
 * @author Pawel
 */
@Named(value = "tableViewController")
@ViewScoped
public class tableViewController implements Serializable {

    List<UserTO> userToList = new ArrayList();
   
    @Inject
    UsersController usersController;
   
    public tableViewController() {
        
    }
    
    @PostConstruct
    public void getData(){
        userToList.clear();
        this.userToList = usersController.getUserToList();
    }
    
    public void addRow(){
        usersController.addRow();
        getData();
    }
    
    public void deleteRow(UserTO userTO){
        usersController.deleteRow(userTO);
        getData();
    }
    
    public void change(UserTO userTO){
        int indexObject = userToList.indexOf(userTO);
        userToList.set(indexObject, userTO);
        usersController.visibleChange(userTO, indexObject);
        getData();
    }
    
    public void sort(String dir){
        usersController.sort(dir);
        getData();
    }

    public List<UserTO> getUserToList() {
        return userToList;
    }

    public void setUserToList(List<UserTO> userToList) {
        this.userToList = userToList;
    }

    public UsersController getUsersController() {
        return usersController;
    }

    public void setUsersController(UsersController usersController) {
        this.usersController = usersController;
    }
    
    
    
}
