/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.Date;
import java.util.List;
import javafx.scene.chart.PieChart;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Solicitud;

/**
 *
 * @author katiro
 */
@Stateless
public class SolicitudFacade extends AbstractFacade<Solicitud> implements SolicitudFacadeLocal {

    @PersistenceContext(unitName = "ticketPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudFacade() {
        super(Solicitud.class);
    }

    @Override
    public Solicitud findByCode(String codigo) {
        return null;
    }

    @Override
    public Solicitud findByEstado() {
        return null;
    }

    @Override
    public List<Solicitud> findByTecnic(int tec) {
        return null;
    }

    @Override
    public List<Solicitud> findByCorrelativo(String Correlativo) {
        return null;
    }

    @Override
    public List<Solicitud> findByDirectory(int idDirectorio) {
        return null;
    }

    @Override
    public List<Solicitud> findByDates(Date fechaInicio, Date fechaFinal) {
        return null;
    }

    @Override
    public List<Solicitud> findByDate(Date fecha) {
        return null;
    }

    @Override
    public List<Solicitud> findByEstado(int idEstado) {
        return null;
    }

    @Override
    public List<Solicitud> findByPrioridad(int idPrioridad) {
        return null;
    }

    @Override
    public List<Solicitud> findByDepartamento(int idDepartamento) {
        return null;
    }

    @Override
    public List<Solicitud> findByCategoria(int idCategoria) {
        return null;
    }

}
