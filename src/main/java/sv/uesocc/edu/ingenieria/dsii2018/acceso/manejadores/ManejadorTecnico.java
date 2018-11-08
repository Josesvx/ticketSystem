package sv.uesocc.edu.ingenieria.dsii2018.acceso.manejadores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.DirectorioFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.cookie.CookieInstance;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Directorio;

/**
 *
 * @author katiro
 */
@Named
@ViewScoped
public class ManejadorTecnico implements Serializable {

    List<Directorio> listaTec, listaTecnicos, listaFiltrada;
    private CookieInstance oreo;
    private Directorio dir;
    protected int idDirectorio;
    @EJB
    private DirectorioFacadeLocal dfl;

    @PostConstruct
//    @Override
    public void init() {
        listaTec=new ArrayList<>();
        oreo = new CookieInstance();
        ObtenerTecnicos();

    }

    public List<Directorio> ObtenerTecnicos() {
        dir = dfl.find(oreo.UsuarioId());
            listaTec = dfl.findByTecFree(dir.getIdDepartamento().getIdDepartamento());
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

    public int getIdDirectorio() {
        return idDirectorio;
    }

    public void setIdDirectorio(int idDirectorio) {
        this.idDirectorio = idDirectorio;
    }
    
    

}
