/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores;

import java.util.List;
import javax.ejb.Local;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Rol;

/**
 *
 * @author katiro
 */
@Local
public interface RolFacadeLocal {

    boolean create(Rol rol);

    boolean edit(Rol rol);

    boolean remove(Rol rol);

    Rol find(Object id);

    List<Rol> findAll();

    List<Rol> findRange(int[] range);

    int count();
    
}
