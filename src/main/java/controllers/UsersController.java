/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package controllers;

import dao.TUzytkownikJpaController;
import entity.TUzytkownik;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.transaction.UserTransaction;
import to.UserTO;

/**
 *
 * @author Pawel
 */
@Named(value = "usersController")
@RequestScoped
public class UsersController {

    public UsersController() {
        refreshData();
    }
    
    @PersistenceContext(name="UsersPU")
    private EntityManager em;
    @Resource
    private UserTransaction utx;        

    List<UserTO> userToList = new ArrayList();
    
    
    @PostConstruct
    public void refreshData(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersPU");
        TUzytkownikJpaController daneDao = new TUzytkownikJpaController(utx,emf);
        List<TUzytkownik> uzytkownikToListLokal = daneDao.findTUzytkownikEntities();
        if(uzytkownikToListLokal!=null){
            userToList.clear();
            for(TUzytkownik tUzytkownik:uzytkownikToListLokal){
                userToList.add(new UserTO(tUzytkownik.getId(),tUzytkownik.getImie(),tUzytkownik.getNazwisko(),false));
            }
        }
    }

    public List<UserTO> getUserToList() {
        return userToList;
    }

    public void setUserToList(List<UserTO> userToList) {
        this.userToList = userToList;
    }
    
    public void visibleChange(UserTO userTO, int index){
        int indexObject = index;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersPU");
        TUzytkownikJpaController daneDao = new TUzytkownikJpaController(utx,emf);
        try{
            daneDao.edit(new TUzytkownik(userTO.getId(),userTO.getImie(),userTO.getNazwisko()));
            userToList.set(indexObject, userTO);
        }catch(Exception ex){
            sendJSFErrorMessage(ex);
        }
    }
    
    public void deleteRow(UserTO userTO){
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersPU");
        TUzytkownikJpaController daneDao = new TUzytkownikJpaController(utx,emf);
        try{
            daneDao.destroy(userTO.getId());
            refreshData();
        }catch(Exception ex){
            sendJSFErrorMessage(ex);
        }
        
    }
    
    public void addRow(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsersPU");
        TUzytkownikJpaController daneDao = new TUzytkownikJpaController(utx,emf); 
        Long id = System.currentTimeMillis();
        TUzytkownik tUzytkowik = new TUzytkownik(id," "," ");
        try{
            daneDao.create(tUzytkowik);
            refreshData();
        }catch(Exception ex){
            sendJSFErrorMessage(ex);
        }
       
    }
    
    public void sendJSFErrorMessage(Exception ex){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        servletContext.log(servletContext.getContextPath()+" : "+ex.toString());
        facesContext.addMessage(null, new FacesMessage(ex.toString()));
    }
    
    public void sort(String dir){
       Comparator<UserTO> comparator;
        comparator = (UserTO a, UserTO b) ->{
            int compareResult = 0;
            if(dir.equals("asc")){
                compareResult = a.getNazwisko().compareTo(b.getNazwisko());
            }else{
                compareResult = b.getNazwisko().compareTo(a.getNazwisko());
            }
            
            return compareResult; 
        };
       Collections.sort(userToList,comparator);
    }
}
