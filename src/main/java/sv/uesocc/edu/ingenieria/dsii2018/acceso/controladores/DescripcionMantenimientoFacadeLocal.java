/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores;

import java.util.List;
import javax.ejb.Local;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.DescripcionMantenimiento;

/**
 *
 * @author katiro
 */
@Local
public interface DescripcionMantenimientoFacadeLocal {

    boolean create(DescripcionMantenimiento descripcionMantenimiento);

    boolean edit(DescripcionMantenimiento descripcionMantenimiento);

    boolean remove(DescripcionMantenimiento descripcionMantenimiento);

    DescripcionMantenimiento find(Object id);

    List<DescripcionMantenimiento> findAll();

    List<DescripcionMantenimiento> findRange(int[] range);

    int count();

    List<DescripcionMantenimiento> findByCorrelativo(int Correlativo);
    
    DescripcionMantenimiento FindBySolicitudEncargado(int idSolicitud, int idDirectorio);

}
