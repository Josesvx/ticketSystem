/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.manejadores;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.DirectorioFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Directorio;

/**
 *
 * @author alexander
 */


@Named
@ViewScoped
public class ManejadorLogin implements Serializable {
    
    @EJB
    private DirectorioFacadeLocal Dfl;
    private Directorio directorio;
    
    @PostConstruct
    public void init(){
        directorio = new Directorio();
    
    }

    public Directorio getDirectorio() {
        return directorio;
    }

    public void setDirectorio(Directorio directorio) {
        this.directorio = directorio;
    }
    
    public String autenticacion(){
        String redireccionar= null;
        Directorio user;
        try{
            user= Dfl.autenticar(directorio);
            if(user != null){
            redireccionar="principal";         
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "aviso", "usuario o contraseña incorrectos"));
            }
       
        }catch(Exception ex){
            throw ex;
        }
        return redireccionar;
    }
    
//    public String autenticar(){
//        String redireccionar= null;
//        Directorio user;
//        try{
//            user= Dfl.autenticar(directorio);
//            if(user != null){
//            redireccionar="principal";         
//            }else{
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "aviso", "usuario o contraseña incorrectos"));
//            }
//       
//        }catch(Exception ex){
//            throw ex;
//        }
//        return redireccionar;
//    }
    
}
