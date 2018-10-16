/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores;

import java.util.List;
import javax.ejb.Local;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.MantenimientoEncargado;

/**
 *
 * @author katiro
 */
@Local
public interface MantenimientoEncargadoFacadeLocal {

    void create(MantenimientoEncargado mantenimientoEncargado);

    void edit(MantenimientoEncargado mantenimientoEncargado);

    void remove(MantenimientoEncargado mantenimientoEncargado);

    MantenimientoEncargado find(Object id);

    List<MantenimientoEncargado> findAll();

    List<MantenimientoEncargado> findRange(int[] range);

    int count();
    
}
