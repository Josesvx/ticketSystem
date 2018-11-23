package sv.uesocc.edu.ingenieria.dsii2018.acceso.manejadores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.Idiomas.ws.SesionDeUsuarioBean;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.DirectorioFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.cookie.CookieInstance;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.cookie.CookieLenguage;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Directorio;

/**
 *
 * @author katiro
 */
@Named
@ViewScoped
public class ManejadorTecnico implements Serializable {

    List<Directorio> listaTec, listaTecIT, listaTecMA, listaTecnicos, listaFiltrada;
    private CookieInstance oreo;
    private Directorio dir;
    protected int idDirectorio;
    private CookieLenguage canCan;
    private SesionDeUsuarioBean bean;
    @EJB
    private DirectorioFacadeLocal dfl;

    @PostConstruct
//    @Override
    public void init() {
        canCan = new CookieLenguage();
        
        bean = new SesionDeUsuarioBean();
        
        bean.cambioIdioma(canCan.getIdioma());
        listaTec = new ArrayList<>();
        oreo = new CookieInstance();
        ObtenerTecnicos();
        ObtenerTecnicosIT();
        ObtenerTecnicosMA();

    }

    public List<Directorio> ObtenerTecnicos() {
        dir = dfl.find(oreo.UsuarioId());

        listaTec = dfl.findByTecFree(dir.getIdDepartamento().getIdDepartamento());
        return listaTec;

    }

    public List<Directorio> ObtenerTecnicosIT() {
        listaTecIT = dfl.findByTecFree(7);
        return listaTecIT;

    }

    public List<Directorio> ObtenerTecnicosMA() {
        listaTecMA = dfl.findByTecFree(4);
        return listaTecMA;

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

    public List<Directorio> getListaTecIT() {
        return listaTecIT;
    }

    public void setListaTecIT(List<Directorio> listaTecIT) {
        this.listaTecIT = listaTecIT;
    }

    public List<Directorio> getListaTecMA() {
        return listaTecMA;
    }

    public void setListaTecMA(List<Directorio> listaTecMA) {
        this.listaTecMA = listaTecMA;
    }

}
