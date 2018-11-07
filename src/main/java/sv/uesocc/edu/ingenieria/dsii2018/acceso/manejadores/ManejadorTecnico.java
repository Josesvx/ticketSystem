package sv.uesocc.edu.ingenieria.dsii2018.acceso.manejadores;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.DirectorioFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.cookie.CookieInstance;
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
    private CookieInstance oreo;
    private Directorio dir;
    @EJB
    private DirectorioFacadeLocal dfl;

    @PostConstruct
    @Override
    public void init() {
        oreo = new CookieInstance();
        ObtenerTecnicos();

    }

    public List<Directorio> ObtenerTecnicos() {

        dir = dfl.find(oreo.UsuarioId());
        if (dir.getIdDepartamento().getIdDepartamento() == 4) {
            listaTec = dfl.findByTecFree(4);
            return listaTec;
        } else {
            listaTec = dfl.findByTecFree(7);
            return listaTec;

        }

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
