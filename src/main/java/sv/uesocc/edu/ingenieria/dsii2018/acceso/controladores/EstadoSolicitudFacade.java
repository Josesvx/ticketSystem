/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Estado;
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.EstadoSolicitud;

/**
 *
 * @author katiro
 */
@Stateless
public class EstadoSolicitudFacade extends AbstractFacade<EstadoSolicitud> implements EstadoSolicitudFacadeLocal {

    @PersistenceContext(unitName = "ticketPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoSolicitudFacade() {
        super(EstadoSolicitud.class);
    }
    
    @Override
    public List<EstadoSolicitud> findByCreation(int idSolicitud) {
        List<EstadoSolicitud> lista = null;
        try {
            Query consulta = em.createNamedQuery("EstadoSolicitud.findByCreacion");
            consulta.setParameter("idSolicitud", idSolicitud);
            lista = consulta.getResultList();
        } catch (Exception ex) {
            throw ex;
        }
        return lista;
    }
    
    @Override
    public List<EstadoSolicitud> findByLastEStado(int idSolicitud) {
        List<EstadoSolicitud> lista = null;
        try {
            Query consulta = em.createNamedQuery("EstadoSolicitud.findByPausadoC");
            consulta.setParameter("idSolicitud", idSolicitud);
            lista = consulta.getResultList();
        } catch (Exception ex) {
            throw ex;
        }
        return lista;
    }
    
}
