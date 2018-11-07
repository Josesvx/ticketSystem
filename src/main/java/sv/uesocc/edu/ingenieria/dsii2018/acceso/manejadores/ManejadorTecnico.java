package sv.uesocc.edu.ingenieria.dsii2018.acceso.manejadores;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.DirectorioFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Directorio;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Solicitud;

/**
 *
 * @author katiro
 */
@Named
@ViewScoped
public class ManejadorTecnico extends ManejadorSolicitud implements Serializable {


    List<Directorio> listaTec, listaTecnicos;
  
    @EJB
    private DirectorioFacadeLocal dfl;
    
    @PostConstruct
    @Override
    public void init() {
        ObtenerTecnicos();
    }
    
    public List<Directorio> ObtenerTecnicos() {
        listaTec = dfl.findByTecFree(solicitudS.getIdDirectorio().getIdDepartamento().getIdDepartamento());
        return listaTec;
       
    }

    public List<Directorio> getListaTec() {
        return listaTec;
    }

    public void setListaTec(List<Directorio> listaTec) {
        this.listaTec = listaTec;
    }

    public List<Directorio> getListaTecnicos() {
        return listaTecnicos;
    }

    public void setListaTecnicos(List<Directorio> listaTecnicos) {
        this.listaTecnicos = listaTecnicos;
    }

 

    
    
    
    
}
