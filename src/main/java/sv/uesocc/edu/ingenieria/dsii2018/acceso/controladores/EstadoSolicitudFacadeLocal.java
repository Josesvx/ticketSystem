/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores;

import java.util.List;
import javax.ejb.Local;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.EstadoSolicitud;

/**
 *
 * @author katiro
 */
@Local
public interface EstadoSolicitudFacadeLocal {

    boolean create(EstadoSolicitud estadoSolicitud);

    boolean edit(EstadoSolicitud estadoSolicitud);

    boolean remove(EstadoSolicitud estadoSolicitud);

    EstadoSolicitud find(Object id);

    List<EstadoSolicitud> findAll();

    List<EstadoSolicitud> findRange(int[] range);

    int count();
    
    List<EstadoSolicitud> findByCreation(int idSolicitud);
    
    List<EstadoSolicitud> findByLastEStado(int idSolicitud);
    
}
