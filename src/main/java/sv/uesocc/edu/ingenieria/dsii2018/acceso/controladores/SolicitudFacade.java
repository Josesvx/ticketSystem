/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.uesocc.edu.ingenieria.dsii2018.acceso.controladores;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        List<Solicitud> listaSol = null;
        try {
            Query consulta = em.createNamedQuery("Solicitud.findByIdDirectorio");
            consulta.setParameter("idDirectorio", idDirectorio);
            listaSol = consulta.getResultList();
        } catch (Exception ex) {
            throw ex;
        }
        return listaSol;
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
        List<Solicitud> listaS = null;
        try {
            Query consulta = em.createNamedQuery("Solicitud.findByEstado");
            consulta.setParameter("idEstado", idEstado);
            listaS = consulta.getResultList();
        } catch (Exception ex) {
            throw ex;
        }
        return listaS;
    }

    @Override
    public List<Solicitud> findByPrioridad(int idPrioridad) {
        List<Solicitud> lista = null;
        try {
            Query consulta = em.createNamedQuery("Solicitud.findByIdPrioridad");
            consulta.setParameter("idPrioridad", idPrioridad);
            lista = consulta.getResultList();
        } catch (Exception ex) {
            throw ex;
        }
        return lista;
    }

    @Override
    public int findByDepartamento(int idDepartamento) {
        int numero = 0;
        try {
            Query consulta = em.createNamedQuery("Solicitud.findByIdDepartamento");
            consulta.setParameter("idDepartamento", idDepartamento);
            numero = Integer.parseInt(String.valueOf(consulta.getSingleResult()));
        } catch (Exception ex) {
            throw ex;
        }
        return numero;
    }

    @Override
    public List<Solicitud> findByCategoria(int idCategoria) {
        List<Solicitud> lista = null;
        try {
            Query consulta = em.createNamedQuery("Solicitud.findByIdCategoria");
            consulta.setParameter("idCategoria", idCategoria);
            lista = consulta.getResultList();
        } catch (Exception ex) {
            throw ex;
        }
        return lista;
    }

}
