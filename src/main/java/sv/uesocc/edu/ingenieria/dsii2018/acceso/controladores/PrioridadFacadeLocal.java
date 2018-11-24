/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores;

import java.util.List;
import javax.ejb.Local;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Prioridad;

/**
 *
 * @author katiro
 */
@Local
public interface PrioridadFacadeLocal {

    boolean create(Prioridad prioridad);

    boolean edit(Prioridad prioridad);

    boolean remove(Prioridad prioridad);

    Prioridad find(Object id);

    List<Prioridad> findAll();

    List<Prioridad> findRange(int[] range);

    int count();
    
}
