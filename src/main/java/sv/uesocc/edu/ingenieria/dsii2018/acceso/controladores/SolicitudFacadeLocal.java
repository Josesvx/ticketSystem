/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Solicitud;

/**
 *
 * @author katiro
 */
@Local
public interface SolicitudFacadeLocal {

    void create(Solicitud solicitud);

    void edit(Solicitud solicitud);

    void remove(Solicitud solicitud);

    Solicitud find(Object id);

    List<Solicitud> findAll();

    List<Solicitud> findRange(int[] range);

    int count();

    Solicitud findByCode(String codigo);

    Solicitud findByEstado();

    List<Solicitud> findByTecnic(int tec);

    List<Solicitud> findByCorrelativo(String Correlativo);

    List<Solicitud> findByDirectory(int idDirectorio);

    List<Solicitud> findByDates(Date fechaInicial, Date fechaFinal);

    List<Solicitud> findByDate(Date fecha);

    List<Solicitud> findByEstado(int idEstado);

    List<Solicitud> findByPrioridad(int idPrioridad);

    List<Solicitud> findByDepartamento(int idDepartemento);

    List<Solicitud> findByCategoria(int idCategoria);
}
