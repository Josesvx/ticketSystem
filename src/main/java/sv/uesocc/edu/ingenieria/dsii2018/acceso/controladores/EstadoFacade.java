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
import sv.uesocc.edu.ingenieria.dsii2018.acceso.definiciones.Solicitud;

/**
 *
 * @author katiro
 */
@Stateless
public class EstadoFacade extends AbstractFacade<Estado> implements EstadoFacadeLocal {

    @PersistenceContext(unitName = "ticketPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoFacade() {
        super(Estado.class);
    }
    
    @Override
    public List<Estado> findLastEstado(int idSolicitud) {
        List<Estado> lista = null;
        try {
            Query consulta = em.createNamedQuery("Estado.findLastEstado");
            consulta.setParameter("idSolicitud", idSolicitud);
            lista = consulta.getResultList();
        } catch (Exception ex) {
            throw ex;
        }
        return lista;
    }
    
    
}
