package sv.uesocc.edu.ingenieria.dsii2018.acceso.manejadores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores.CategoriaFacadeLocal;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Categoria;

/**
 *
 * @author katiro
 */
@Named
@ViewScoped
public class ManejadorCategoria implements Serializable {

    @EJB
    private CategoriaFacadeLocal cfl;
    private Categoria categoria;
    private List<Categoria> listaCat;

    public List<Categoria> ObtenerCategoria() {
        List<Categoria> lista = cfl.findAll();
        if (lista != null && !lista.isEmpty()) {
            listaCat = lista;
        } else {
            listaCat = new ArrayList<>();
        }
        return listaCat;
    }

}
